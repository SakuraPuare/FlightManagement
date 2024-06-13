package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.pojo.vo.AllCountStatisticVO;
import com.sakurapuare.flightmanagement.pojo.vo.GoodStatisticVO;
import com.sakurapuare.flightmanagement.services.*;
import com.sakurapuare.flightmanagement.services.user.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/statistics")
@Tag(name = "Statistics", description = "The Statistic API")
public class StatisticController {

    private final AirlineService airlineService;

    private final MerchantService merchantService;

    private final PassengerService passengerService;

    private final StaffService staffService;

    private final UserService userService;

    private final FlightService flightService;

    private final GoodService goodService;

    private final LuggageService luggageService;

    private final OrderService orderService;

    private final RequestService requestService;

    private final TicketService ticketService;

    public StatisticController(AirlineService airlineService, MerchantService merchantService,
            PassengerService passengerService, StaffService staffService, UserService userService,
            FlightService flightService, GoodService goodService, LuggageService luggageService,
            OrderService orderService, RequestService requestService, TicketService ticketService) {
        this.airlineService = airlineService;
        this.merchantService = merchantService;
        this.passengerService = passengerService;
        this.staffService = staffService;
        this.userService = userService;
        this.flightService = flightService;
        this.goodService = goodService;
        this.luggageService = luggageService;
        this.orderService = orderService;
        this.requestService = requestService;
        this.ticketService = ticketService;
    }

    @GetMapping("/count")
    public Response<AllCountStatisticVO> getAllCount() {
        AllCountStatisticVO allCountVO = new AllCountStatisticVO();
        allCountVO.setAirlineCount(airlineService.count());
        allCountVO.setMerchantCount(merchantService.count());
        allCountVO.setPassengerCount(passengerService.count());
        allCountVO.setStaffCount(staffService.count());
        allCountVO.setUserCount(userService.count());
        allCountVO.setFlightCount(flightService.count());
        allCountVO.setGoodsCount(goodService.count());
        allCountVO.setLuggageCount(luggageService.count());
        allCountVO.setOrderCount(orderService.count());
        allCountVO.setRequestCount(requestService.count());
        allCountVO.setTicketCount(ticketService.count());

        return Response.success(allCountVO);
    }

    @GetMapping("/count/{type}")
    public Response<Long> getCountByType(@PathVariable String type) {
        long count;
        switch (type) {
            case "airline":
                count = airlineService.count();
                break;
            case "merchant":
                count = merchantService.count();
                break;
            case "passenger":
                count = passengerService.count();
                break;
            case "staff":
                count = staffService.count();
                break;
            case "user":
                count = userService.count();
                break;
            case "flight":
                count = flightService.count();
                break;
            case "good":
                count = goodService.count();
                break;
            case "luggage":
                count = luggageService.count();
                break;
            case "order":
                count = orderService.count();
                break;
            case "request":
                count = requestService.count();
                break;
            case "ticket":
                count = ticketService.count();
                break;
            default:
                return Response.error("Invalid type");
        }
        return Response.success(count);
    }

    // good sell statistics
    @GetMapping("/good")
    public Response<GoodStatisticVO> getGoodSellStatistics() {
        GoodStatisticVO goodStatisticVO = new GoodStatisticVO();
        goodStatisticVO.setGoodsCount(goodService.count());
        goodStatisticVO.setGoodsTotalPrice(goodService.getTotalPrice());
        return Response.success(goodStatisticVO);
    }

}
