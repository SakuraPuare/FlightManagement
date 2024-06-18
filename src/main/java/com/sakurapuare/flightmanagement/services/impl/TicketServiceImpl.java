package com.sakurapuare.flightmanagement.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakurapuare.flightmanagement.mapper.OrderMapper;
import com.sakurapuare.flightmanagement.mapper.TicketMapper;
import com.sakurapuare.flightmanagement.pojo.dto.TicketDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Flight;
import com.sakurapuare.flightmanagement.pojo.entity.Order;
import com.sakurapuare.flightmanagement.pojo.entity.Ticket;
import com.sakurapuare.flightmanagement.pojo.entity.user.Airline;
import com.sakurapuare.flightmanagement.services.FlightService;
import com.sakurapuare.flightmanagement.services.TicketService;
import com.sakurapuare.flightmanagement.services.user.AirlineService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketMapper ticketMapper;

    private final OrderMapper orderMapper;

    private AirlineService airlineService;

    private FlightService flightService;

    public TicketServiceImpl(TicketMapper ticketMapper, OrderMapper orderMapper, AirlineService airlineService,
            FlightService flightService) {
        this.ticketMapper = ticketMapper;
        this.orderMapper = orderMapper;
        this.airlineService = airlineService;
        this.flightService = flightService;
    }

    @Override
    public List<Ticket> getTicketsByPagination(int page, int count) {
        Page<Ticket> pagination = new Page<>(page, count);
        return ticketMapper.selectPage(pagination, null).getRecords();
    }

    @Override
    public Ticket getTicketById(long id) {
        return ticketMapper.selectById(id);
    }

    @Override
    public Ticket getTicketByFlightIdAndSeatClass(long flightId, String seatClass) {
        return ticketMapper.selectOne(
                new QueryWrapper<Ticket>()
                        .eq("flight_id", flightId)
                        .eq("seat_class", seatClass));
    }

    @Override
    public boolean isTicketSoldOut(Ticket ticket) {
        return ticket.getQuota() <= 0;
    }

    @Override
    public void addTicket(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketDTO, ticket);
        ticketMapper.insert(ticket);
    }

    @Override
    public void updateTicket(long id, TicketDTO ticketDTO) {
        Ticket ticket = getTicketById(id);
        if (ticket == null) {
            return;
        }
        BeanUtils.copyProperties(ticketDTO, ticket);
        ticketMapper.updateById(ticket);
    }

    @Override
    public void deleteTicket(long id) {
        // find all orders related to this ticket
        List<Order> orders = orderMapper.selectList(
                new QueryWrapper<Order>()
                        .eq("ticket_id", id));

        for (Order order : orders) {
            orderMapper.deleteById(order.getId());
        }

        ticketMapper.deleteById(id);
    }

    @Override
    public void sellTicket(Ticket ticket) {
        ticket.setQuota(ticket.getQuota() - 1);
        ticketMapper.updateById(ticket);
    }

    @Override
    public void refundTicket(Ticket ticket) {
        ticket.setQuota(ticket.getQuota() + 1);
        ticketMapper.updateById(ticket);
    }

    @Override
    public long count() {
        return ticketMapper.selectCount(null);
    }

    @Override
    public List<Ticket> getTicketsByFlightId(long id) {
        return ticketMapper.selectList(
                new QueryWrapper<Ticket>()
                        .eq("flight_id", id));
    }

    @Override
    public List<Ticket> getTicketsByPaginationAndUserId(int page, int count, Long userId) {
        Airline airline = airlineService.getAirlineByUserId(userId);
        List<Flight> flights = flightService.getFlightsListByAirlineId(airline.getAirlineId());
        Page<Ticket> pagination = new Page<>(page, count);
        return ticketMapper.selectPage(pagination,
                new QueryWrapper<Ticket>()
                        .in("flight_id", flights.stream().map(
                                Flight::getId).toArray()))
                .getRecords();
    }

    @Override
    public Ticket getTicketByIdAndUserId(long id, Long userId) {
        Airline airline = airlineService.getAirlineByUserId(userId);
        List<Flight> flights = flightService.getFlightsListByAirlineId(airline.getAirlineId());
        return ticketMapper.selectOne(
                new QueryWrapper<Ticket>()
                        .eq("id", id)
                        .in("flight_id", flights.stream().map(
                                Flight::getId).toArray()));
    }

}
