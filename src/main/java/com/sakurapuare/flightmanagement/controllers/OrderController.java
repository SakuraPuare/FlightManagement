package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.pojo.entity.Flight;
import com.sakurapuare.flightmanagement.pojo.entity.Order;
import com.sakurapuare.flightmanagement.pojo.entity.Ticket;
import com.sakurapuare.flightmanagement.pojo.entity.user.Airline;
import com.sakurapuare.flightmanagement.pojo.vo.OrderVO;
import com.sakurapuare.flightmanagement.services.FlightService;
import com.sakurapuare.flightmanagement.services.OrderService;
import com.sakurapuare.flightmanagement.services.TicketService;
import com.sakurapuare.flightmanagement.services.user.AirlineService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/orders")
@Tag(name = "Order Controller", description = "Order API Endpoints")
public class OrderController {

    private final OrderService orderService;

    private final TicketService ticketService;

    private final FlightService flightService;

    private final AirlineService airlineService;

    public OrderController(OrderService orderService, TicketService ticketService, FlightService flightService,
            AirlineService airlineService) {
        this.orderService = orderService;
        this.ticketService = ticketService;
        this.flightService = flightService;
        this.airlineService = airlineService;
    }

    // Get my order list
    @GetMapping("/list")
    public Response<List<OrderVO>> getOrderList(@RequestParam("page") int page, @RequestParam("count") int count,
            HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());

        List<Order> orders = orderService.getOrdersByPaginationAndUserId(page, count, userId);

        List<OrderVO> orderVOList = new ArrayList<>();

        Map<Long, Ticket> ticketMap = new HashMap<>();
        Map<Long, Airline> airlineMap = new HashMap<>();
        Map<Long, Flight> flightMap = new HashMap<>();
        for (Order order : orders) {
            Ticket ticket = ticketMap.computeIfAbsent(order.getTicketId(),
                    id -> ticketService.getTicketById(id));
            Flight flight = flightMap.computeIfAbsent(ticket.getFlightId(),
                    id -> flightService.getFlightById(id));
            Airline airline = airlineMap.computeIfAbsent(flight.getAirlineId(),
                    id -> airlineService.getAirlineById(id));

            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(airline, orderVO);
            BeanUtils.copyProperties(flight, orderVO);
            BeanUtils.copyProperties(ticket, orderVO);
            BeanUtils.copyProperties(order, orderVO);

            orderVOList.add(orderVO);
        }

        return Response.success(orderVOList);
    }

    @GetMapping("/{id}")
    public Response<OrderVO> getOrder(@PathVariable("id") long id,
            HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());

        Order order = orderService.getOrderByIdAndUserId(id, userId);
        if (order == null) {
            return Response.error(404, "Order not found");
        }

        Ticket ticket = ticketService.getTicketById(order.getTicketId());
        Flight flight = flightService.getFlightById(ticket.getFlightId());
        Airline airline = airlineService.getAirlineById(flight.getAirlineId());

        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(airline, orderVO);
        BeanUtils.copyProperties(flight, orderVO);
        BeanUtils.copyProperties(ticket, orderVO);
        BeanUtils.copyProperties(order, orderVO);

        return Response.success(orderVO);
    }

    // Add order
    @Transactional
    @PostMapping("/{id}")
    public Response<Void> addOrder(@PathVariable("id") long id,
            HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());

        Ticket ticket = ticketService.getTicketById(id);
        if (ticket == null) {
            return Response.error(404, "Ticket not found");
        }

        if (ticketService.isTicketSoldOut(ticket)) {
            return Response.error("Ticket sold out");
        }

        if (orderService.isOrderConflict(id, userId)) {
            return Response.error("Order conflict");
        }

        orderService.addOrder(id, userId);

        return Response.success("Order added successfully");
    }

    @Transactional
    @PostMapping("/{id}/pay")
    public Response<Void> payOrder(@PathVariable("id") long id,
            HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());

        Order order = orderService.getOrderByIdAndUserId(id, userId);
        if (order == null) {
            return Response.error(404, "Order not found");
        }

        orderService.payOrder(order);

        return Response.success("Order paid successfully");
    }

    @Transactional
    @DeleteMapping("/{id}")
    public Response<Void> deleteOrder(@PathVariable("id") long id,
            HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());

        Order order = orderService.getOrderByIdAndUserId(id, userId);
        if (order == null) {
            return Response.error(404, "Order not found");
        }

        orderService.deleteOrder(order);

        return Response.success("Order deleted successfully");
    }

}
