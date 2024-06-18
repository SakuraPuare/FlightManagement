package com.sakurapuare.flightmanagement.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakurapuare.flightmanagement.mapper.OrderMapper;
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
    public List<Order> getOrdersByPaginationAndUserId(int page, int count, long userId) {
        Page<Order> pagination = new Page<>(page, count);
        return orderMapper.selectPage(pagination,
                        new QueryWrapper<Order>()
                                .eq("user_id", userId))
                .getRecords();
    }

    @Override
    public Order getOrderByIdAndUserId(long id, long userId) {
        return orderMapper.selectOne(
                new QueryWrapper<Order>()
                        .eq("id", id)
                        .eq("user_id", userId));
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
                        .eq("user_id", userId)
                        .ne("status", "canceled"));
        if (orders.isEmpty()) {
            return false;
        }

        // find the flight
        Flight flight = flightService.getFlightById(ticket.getFlightId());
        // check if the user has bought the same ticket
        for (Order order : orders) {
            Ticket ticket1 = ticketService.getTicketById(order.getTicketId());
            Flight flight1 = flightService.getFlightById(ticket1.getFlightId());
            if (flight.getId().equals(flight1.getId())) {
                return true;
            }
        }

        // check if you have a conflict time
        for (Order order : orders) {
            LocalDateTime newDepartureTime = flight.getDateOfDeparture();
            Ticket ticket1 = ticketService.getTicketById(order.getTicketId());
            Flight flight1 = flightService.getFlightById(ticket1.getFlightId());
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

    @Override
    public void cancelOrder(Order order) {
        order.setStatus("canceled");
        Ticket ticket = ticketService.getTicketById(order.getTicketId());
        ticketService.refundTicket(ticket);
        orderMapper.updateById(order);
    }

    @Override
    public List<Order> getOrdersAll(long userId) {
        return orderMapper.selectList(
                new QueryWrapper<Order>()
                        .eq("user_id", userId));
    }

    @Override
    public long count() {
        return orderMapper.selectCount(null);
    }

    @Override
    public List<Order> getOrdersByFlightId(Long flightId) {
        return orderMapper.selectList(
                new QueryWrapper<Order>()
                        .eq("flight_id", flightId));
    }

    @Override
    public List<Order> getOrdersByPagination(int page, int count) {
        Page<Order> pagination = new Page<>(page, count);
        return orderMapper.selectPage(pagination, null).getRecords();
    }

}