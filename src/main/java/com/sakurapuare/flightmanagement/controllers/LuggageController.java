package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.pojo.dto.LuggageDTO;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Luggage;
import com.sakurapuare.flightmanagement.pojo.entity.Order;
import com.sakurapuare.flightmanagement.services.LuggageService;
import com.sakurapuare.flightmanagement.services.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/luggages")
@Tag(name = "Luggage Controller", description = "Luggage API Endpoints")
public class LuggageController {

    private final LuggageService luggageService;

    private final OrderService orderService;

    public LuggageController(LuggageService luggageService, OrderService orderService) {
        this.luggageService = luggageService;
        this.orderService = orderService;
    }

    @GetMapping("/my")
    public Response<List<Luggage>> getMyLuggageList(@Valid @RequestBody PaginationDTO paginationDTO,
                                                    HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());

        return Response.success(luggageService.getLuggageByPaginationAndId(paginationDTO, userId));
    }

    @GetMapping("/list")
    public Response<List<Luggage>> getLuggageList(@Valid @RequestBody PaginationDTO paginationDTO) {
        return Response.success(luggageService.getLuggageByPagination(paginationDTO));
    }

    @GetMapping("/{id}")
    public Response<Luggage> getLuggageById(@PathVariable(name = "id") long id,
                                            HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());

        Luggage luggage = luggageService.getLuggageByIdAndUserId(id, userId);
        if (luggage == null) {
            return Response.error("Luggage not found");
        }

        return Response.success(luggage);
    }

    @PostMapping("/")
    public Response<Void> addLuggage(@Valid @RequestBody LuggageDTO luggageDTO,
                                     HttpServletRequest request) {
        long staffId = Long.parseLong(request.getAttribute("userId").toString());
        long userId = luggageDTO.getUserId();
        Order order = orderService.getOrderByIdAndUserId(luggageDTO.getOrderId(), userId);
        List<Order> orders = orderService.getOrdersAll(userId);

        // if order not found or order is not in orders list
        if (order == null || !orders.contains(order)) {
            return Response.error("Order not found");
        }

        luggageService.addLuggage(luggageDTO, staffId);
        return Response.success("Luggage added successfully");
    }

    @PutMapping("/{id}")
    public Response<Void> updateLuggage(@PathVariable(name = "id") long id, @RequestBody LuggageDTO luggageDTO,
                                        HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());

        Luggage luggage = luggageService.getLuggageByIdAndUserId(id, userId);
        if (luggage == null) {
            return Response.error("Luggage not found");
        }

        luggageService.updateLuggage(luggage, luggageDTO, userId);
        return Response.success("Luggage updated successfully");
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteLuggage(@PathVariable(name = "id") long id,
                                        HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());

        Luggage luggage = luggageService.getLuggageByIdAndUserId(id, userId);
        if (luggage == null) {
            return Response.error("Luggage not found");
        }

        luggageService.deleteLuggage(id, userId);
        return Response.success("Luggage deleted successfully");
    }

}
