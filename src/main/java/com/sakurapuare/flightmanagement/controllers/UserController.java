package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.pojo.entity.user.*;
import com.sakurapuare.flightmanagement.services.user.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/user/list")
    public Response<List<User>> listUsers(@RequestParam("page") int page, @RequestParam("count") int count) {
        return Response.success(userService.getUserByPagination(page, count));
    }

    @GetMapping("/airline/{id}")
    public Response<Airline> getAirline(@PathVariable Long id) {
        Airline airline = airlineService.getAirlineById(id);
        if (airline == null) {
            return Response.error("Airline not found");
        }
        return Response.success(airline);
    }

    @GetMapping("/airline/list")
    public Response<List<Airline>> listAirlines(@RequestParam("page") int page, @RequestParam("count") int count) {
        return Response.success(airlineService.getAirlinesByPagination(page, count));
    }

    @GetMapping("/merchant/{id}")
    public Response<Merchant> getMerchant(@PathVariable Long id) {
        Merchant merchant = merchantService.getMerchantById(id);
        if (merchant == null) {
            return Response.error("Merchant not found");
        }
        return Response.success(merchant);
    }

    @GetMapping("/merchant/list")
    public Response<List<Merchant>> listMerchants(@RequestParam("page") int page, @RequestParam("count") int count) {
        return Response.success(merchantService.getMerchantsByPagination(page, count));
    }

    @GetMapping("/passenger/{id}")
    public Response<Passenger> getPassenger(@PathVariable Long id) {
        Passenger passenger = passengerService.getPassengerById(id);
        if (passenger == null) {
            return Response.error("Passenger not found");
        }
        return Response.success(passenger);
    }

    @GetMapping("/passenger/list")
    public Response<List<Passenger>> listPassengers(@RequestParam("page") int page, @RequestParam("count") int count) {
        return Response.success(passengerService.getPassengersByPagination(page, count));
    }

    @GetMapping("/staff/{id}")
    public Response<Staff> getStaff(@PathVariable Long id) {
        Staff staff = staffService.getStaffById(id);
        if (staff == null) {
            return Response.error("Staff not found");
        }
        return Response.success(staff);
    }

    @GetMapping("/staff/list")
    public Response<List<Staff>> listStaff(@RequestParam("page") int page, @RequestParam("count") int count) {
        return Response.success(staffService.getStaffByPagination(page, count));
    }
}
