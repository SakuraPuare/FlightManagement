package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Order;
import com.sakurapuare.flightmanagement.pojo.entity.Ticket;
import com.sakurapuare.flightmanagement.services.OrderService;
import com.sakurapuare.flightmanagement.services.TicketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/orders")
@Tag(name = "Order Controller", description = "Order API Endpoints")
public class OrderController {

    private final OrderService orderService;

    private final TicketService ticketService;

    public OrderController(OrderService orderService, TicketService ticketService) {
        this.orderService = orderService;
        this.ticketService = ticketService;
    }

    // Get my order list
    @GetMapping("/list")
    public Response<List<Order>> getOrderList(@Valid @RequestBody PaginationDTO paginationDTO,
                                              HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());

        List<Order> orders = orderService.getOrdersByPaginationAndUserId(paginationDTO, userId);
        return Response.success(orders);
    }

    @GetMapping("/{id}")
    public Response<Order> getOrder(@PathVariable("id") long id,
                                    HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());

        Order order = orderService.getOrderByIdAndUserId(id, userId);
        if (order == null) {
            return Response.error(404, "Order not found");
        }

        return Response.success(order);
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
    @PostMapping("/pay/{id}")
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
