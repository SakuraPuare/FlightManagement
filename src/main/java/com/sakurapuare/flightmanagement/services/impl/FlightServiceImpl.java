package com.sakurapuare.flightmanagement.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakurapuare.flightmanagement.mapper.FlightMapper;
import com.sakurapuare.flightmanagement.mapper.OrderMapper;
import com.sakurapuare.flightmanagement.mapper.TicketMapper;
import com.sakurapuare.flightmanagement.pojo.dto.FlightDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Flight;
import com.sakurapuare.flightmanagement.pojo.entity.Order;
import com.sakurapuare.flightmanagement.pojo.entity.Ticket;
import com.sakurapuare.flightmanagement.services.FlightService;
import com.sakurapuare.flightmanagement.services.TicketService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightMapper flightMapper;

    private final TicketMapper ticketMapper;

    private final OrderMapper orderMapper;

    public FlightServiceImpl(FlightMapper flightMapper, TicketMapper ticketMapper, OrderMapper orderMapper,
            TicketService ticketService) {
        this.flightMapper = flightMapper;
        this.ticketMapper = ticketMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public Flight getFlightById(long id) {
        return flightMapper.selectById(id);
    }

    @Override
    public List<Flight> getFlightsByPagination(int page, int count) {
        Page<Flight> pagination = new Page<>(page, count);
        return flightMapper.selectPage(pagination, null).getRecords();
    }

    @Override
    public List<Flight> search(String query) {
        Set<Flight> flights = new HashSet<>();
        flights.addAll(flightMapper.selectList(new QueryWrapper<Flight>().like("flight_number", query)));
        flights.addAll(flightMapper.selectList(new QueryWrapper<Flight>().like("departure_city", query)));
        flights.addAll(flightMapper.selectList(new QueryWrapper<Flight>().like("arrival_city", query)));
        return List.copyOf(flights);
    }

    @Override
    public void addFlight(FlightDTO flightDTO) {
        Flight flight = new Flight();
        BeanUtils.copyProperties(flightDTO, flight);
        flightMapper.insert(flight);

    }

    @Override
    public void updateFlight(Flight flight, FlightDTO flightDTO) {
        BeanUtils.copyProperties(flightDTO, flight);
        flightMapper.updateById(flight);
    }

    @Override
    public void deleteFlight(long id) {
        // find all tickets related to this flight
        List<Ticket> tickets = ticketMapper.selectList(new QueryWrapper<Ticket>().eq("flight_id", id));
        Set<Long> flightIds = new HashSet<>();
        for (Ticket ticket : tickets) {
            flightIds.add(ticket.getFlightId());
        }

        // find all orders related to all tickets
        List<Order> orders = new ArrayList<>();
        for (Long flightId : flightIds) {
            orders.addAll(orderMapper.selectList(new QueryWrapper<Order>().eq("ticket_id", flightId)));
        }

        for (Order order : orders) {
            orderMapper.deleteById(order.getId());
        }

        for (Ticket ticket : tickets) {
            ticketMapper.deleteById(ticket.getId());
        }

        flightMapper.deleteById(id);
    }

    @Override
    public long count() {
        return flightMapper.selectCount(null);
    }

}
