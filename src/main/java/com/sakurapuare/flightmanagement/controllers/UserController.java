package com.sakurapuare.flightmanagement.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.constant.Role;
import com.sakurapuare.flightmanagement.pojo.entity.user.Airline;
import com.sakurapuare.flightmanagement.pojo.entity.user.Merchant;
import com.sakurapuare.flightmanagement.pojo.entity.user.Passenger;
import com.sakurapuare.flightmanagement.pojo.entity.user.Staff;
import com.sakurapuare.flightmanagement.pojo.entity.user.User;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.AirlineInfo;
import com.sakurapuare.flightmanagement.services.AuthService;
import com.sakurapuare.flightmanagement.services.user.AirlineService;
import com.sakurapuare.flightmanagement.services.user.MerchantService;
import com.sakurapuare.flightmanagement.services.user.PassengerService;
import com.sakurapuare.flightmanagement.services.user.StaffService;
import com.sakurapuare.flightmanagement.services.user.UserService;
import com.sakurapuare.flightmanagement.utils.RoleUtils;

@RestController
@Slf4j
@RequestMapping("/users")
@Tag(name = "User Controller", description = "User API Endpoints")
public class UserController {

    private final UserService userService;

    private final AirlineService airlineService;

    private final MerchantService merchantService;

    private final PassengerService passengerService;

    private final StaffService staffService;

    public UserController(UserService userService, AirlineService airlineService,
            MerchantService merchantService, PassengerService passengerService, StaffService staffService) {
        this.userService = userService;
        this.airlineService = airlineService;
        this.merchantService = merchantService;
        this.passengerService = passengerService;
        this.staffService = staffService;
    }

    @GetMapping("/user/{id}")
    public Response<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return Response.error("User not found");
        }
        return Response.success(user);
    }

    @GetMapping("/airline/{id}")
    public Response<Airline> getAirline(@PathVariable Long id) {
        Airline airline = airlineService.getAirlineById(id);
        if (airline == null) {
            return Response.error("Airline not found");
        }
        return Response.success(airline);
    }

    @GetMapping("/merchant/{id}")
    public Response<Merchant> getMerchant(@PathVariable Long id) {
        Merchant merchant = merchantService.getMerchantById(id);
        if (merchant == null) {
            return Response.error("Merchant not found");
        }
        return Response.success(merchant);
    }

    @GetMapping("/passenger/{id}")
    public Response<Passenger> getPassenger(@PathVariable Long id) {
        Passenger passenger = passengerService.getPassengerById(id);
        if (passenger == null) {
            return Response.error("Passenger not found");
        }
        return Response.success(passenger);
    }

    @GetMapping("/staff/{id}")
    public Response<Staff> getStaff(@PathVariable Long id) {
        Staff staff = staffService.getStaffById(id);
        if (staff == null) {
            return Response.error("Staff not found");
        }
        return Response.success(staff);
    }

}
