package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.pojo.dto.LuggageDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Luggage;
import com.sakurapuare.flightmanagement.pojo.entity.Order;
import com.sakurapuare.flightmanagement.pojo.entity.user.Staff;
import com.sakurapuare.flightmanagement.pojo.entity.user.User;
import com.sakurapuare.flightmanagement.pojo.vo.LuggageVO;
import com.sakurapuare.flightmanagement.services.LuggageService;
import com.sakurapuare.flightmanagement.services.OrderService;
import com.sakurapuare.flightmanagement.services.user.StaffService;
import com.sakurapuare.flightmanagement.services.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/luggages")
@Tag(name = "Luggage Controller", description = "Luggage API Endpoints")
public class LuggageController {

    private final LuggageService luggageService;

    private final OrderService orderService;

    private final StaffService staffService;

    private final UserService userService;

    public LuggageController(LuggageService luggageService, OrderService orderService, StaffService staffService,
                             UserService userService) {
        this.luggageService = luggageService;
        this.orderService = orderService;
        this.staffService = staffService;
        this.userService = userService;
    }

    @GetMapping("/my")
    public Response<List<LuggageVO>> getMyLuggageList(@RequestParam("page") int page, @RequestParam("count") int count,
                                                      HttpServletRequest request) {

        long userId = Long.parseLong(request.getAttribute("userId").toString());

        List<LuggageVO> luggageVOList = new ArrayList<>();
        for (Luggage luggage : luggageService.getLuggageByPaginationAndId(page, count, userId)) {
            Staff staff = staffService.getStaffById(luggage.getStaffId());
            User user = userService.getUserById(staff.getUserId());

            LuggageVO luggageVO = new LuggageVO();
            BeanUtils.copyProperties(luggage, luggageVO);
            BeanUtils.copyProperties(staff, luggageVO);
            BeanUtils.copyProperties(user, luggageVO);

            luggageVOList.add(luggageVO);
        }

        return Response.success(luggageVOList);
    }

    @GetMapping("/list")
    public Response<List<LuggageVO>> getLuggageList(@RequestParam("page") int page, @RequestParam("count") int count) {

        List<LuggageVO> luggageVOList = new ArrayList<>();
        for (Luggage luggage : luggageService.getLuggageByPagination(page, count)) {
            Staff staff = staffService.getStaffById(luggage.getStaffId());
            User user = userService.getUserById(staff.getUserId());

            LuggageVO luggageVO = new LuggageVO();
            BeanUtils.copyProperties(luggage, luggageVO);
            BeanUtils.copyProperties(staff, luggageVO);
            BeanUtils.copyProperties(user, luggageVO);

            luggageVOList.add(luggageVO);
        }

        return Response.success(luggageVOList);
    }

    @GetMapping("/{id}")
    public Response<LuggageVO> getLuggageById(@PathVariable(name = "id") long id,
                                              HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());

        Luggage luggage = luggageService.getLuggageByIdAndUserId(id, userId);
        if (luggage == null) {
            return Response.error("Luggage not found");
        }

        Staff staff = staffService.getStaffById(luggage.getStaffId());

        LuggageVO luggageVO = new LuggageVO();
        BeanUtils.copyProperties(luggage, luggageVO);
        BeanUtils.copyProperties(staff, luggageVO);

        return Response.success(luggageVO);
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
