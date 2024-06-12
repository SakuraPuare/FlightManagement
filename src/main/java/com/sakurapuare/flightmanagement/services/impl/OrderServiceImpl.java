package com.sakurapuare.flightmanagement.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakurapuare.flightmanagement.mapper.OrderMapper;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Flight;
import com.sakurapuare.flightmanagement.pojo.entity.Order;
import com.sakurapuare.flightmanagement.pojo.entity.Ticket;
import com.sakurapuare.flightmanagement.services.FlightService;
import com.sakurapuare.flightmanagement.services.OrderService;
import com.sakurapuare.flightmanagement.services.TicketService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    private final TicketService ticketService;

    private final FlightService flightService;

    public OrderServiceImpl(OrderMapper orderMapper, TicketService ticketService, FlightService flightService) {
        this.orderMapper = orderMapper;
        this.ticketService = ticketService;
        this.flightService = flightService;
    }

    @Override
    public List<Order> getOrdersByPaginationAndUserId(PaginationDTO paginationDTO, long userId) {
        Page<Order> page = new Page<>(paginationDTO.getPage(), paginationDTO.getCount());
        return orderMapper.selectPage(page,
                new QueryWrapper<Order>()
                        .eq("user_id", userId))
                .getRecords();
    }

    @Override
    public Order getOrderByIdAndUserId(long id, long userId) {
        Order order = orderMapper.selectById(id);
        if (order == null || order.getUserId() != userId) {
            return null;
        }
        return order;
    }

    @Override
    public void addOrder(long ticketId, long userId) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        if (ticket == null) {
            return;
        }

        if (ticketService.isTicketSoldOut(ticket)) {
            return;
        }

        Order order = new Order();
        order.setTicketId(ticketId);
        order.setUserId(userId);

        // sell a ticket
        ticketService.sellTicket(ticket);

        orderMapper.insert(order);
    }

    @Override
    public void deleteOrder(Order order) {
        Ticket ticket = ticketService.getTicketById(order.getTicketId());
        ticketService.refundTicket(ticket);
        orderMapper.deleteById(order);
    }

    @Override
    public boolean isOrderConflict(long ticketId, long userId) {
        Ticket ticket = ticketService.getTicketById(ticketId);

        // get the order list of the user
        List<Order> orders = orderMapper.selectList(
                new QueryWrapper<Order>()
                        .eq("user_id", userId));
        if (orders.isEmpty()) {
            return false;
        }

        // check if the user has bought the same ticket
        for (Order order : orders) {
            if (order.getTicketId() == ticketId) {
                return true;
            }
        }

        // find the flight
        Flight flight = flightService.getFlightById(ticket.getFlightId());
        // check if you have a conflict time
        for (Order order : orders) {
            LocalDateTime newDepartureTime = flight.getDateOfDeparture();
            Flight flight1 = flightService.getFlightById(order.getTicketId());
            LocalDateTime departureTime = flight1.getDateOfDeparture();
            LocalDateTime arrivalTime = flight1.getDateOfDeparture().plusMinutes(flight1.getEstimatedTravelTime());
            if (newDepartureTime.isAfter(departureTime) && newDepartureTime.isBefore(arrivalTime)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void payOrder(Order order) {
        order.setStatus("paid");
        orderMapper.updateById(order);
    }

}