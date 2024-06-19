package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.pojo.dto.FlightDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Flight;
import com.sakurapuare.flightmanagement.pojo.entity.user.Airline;
import com.sakurapuare.flightmanagement.pojo.vo.FlightVO;
import com.sakurapuare.flightmanagement.services.FlightService;
import com.sakurapuare.flightmanagement.services.user.AirlineService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    private final AirlineService airlineService;

    public FlightController(FlightService flightService, AirlineService airlineService) {
        this.flightService = flightService;
        this.airlineService = airlineService;
    }

    @GetMapping("/list")
    public Response<List<FlightVO>> getFlightList(@RequestParam("page") int page, @RequestParam("count") int count) {

        List<Flight> flightList = flightService.getFlightsByPagination(page, count);
        List<FlightVO> flightVOList = new ArrayList<>();

        Map<Long, Airline> airlineMap = new HashMap<>();
        for (Flight flight : flightList) {
            Airline airline = airlineMap.computeIfAbsent(flight.getAirlineId(),
                    id -> airlineService.getAirlineById(id));

            FlightVO flightVO = new FlightVO();
            BeanUtils.copyProperties(flight, flightVO);
            BeanUtils.copyProperties(airline, flightVO);
            flightVOList.add(flightVO);
        }

        return Response.success(flightVOList);
    }

    @GetMapping("/my")
    public Response<List<FlightVO>> getMyFlightList(@RequestParam("page") int page, @RequestParam("count") int count,
                                                    HttpServletRequest request) {
        Long userId = Long.parseLong(request.getAttribute("userId").toString());

        List<Flight> flightList = flightService.getFlightsByPaginationAndUserId(page, count, userId);
        List<FlightVO> flightVOList = new ArrayList<>();

        Map<Long, Airline> airlineMap = new HashMap<>();
        for (Flight flight : flightList) {
            Airline airline = airlineMap.computeIfAbsent(flight.getAirlineId(),
                    id -> airlineService.getAirlineById(id));

            FlightVO flightVO = new FlightVO();
            BeanUtils.copyProperties(flight, flightVO);
            BeanUtils.copyProperties(airline, flightVO);
            flightVOList.add(flightVO);
        }

        return Response.success(flightVOList);
    }

    @GetMapping("/{id}")
    public Response<FlightVO> getFlightById(@PathVariable(name = "id") long id, HttpServletRequest request) {
        Long userId = Long.parseLong(request.getAttribute("userId").toString());
        Flight flight = flightService.getFlightById(id);
        if (flight == null || !flight.getAirlineId().equals(userId)) {
            return Response.error("Flight not found");
        }

        Airline airline = airlineService.getAirlineById(flight.getAirlineId());
        FlightVO flightVO = new FlightVO();
        BeanUtils.copyProperties(flight, flightVO);
        BeanUtils.copyProperties(airline, flightVO);

        return Response.success(flightVO);
    }

    @PostMapping("/")
    public Response<String> addFlight(@Valid @RequestBody FlightDTO flightDTO, HttpServletRequest request) {
        Long userId = Long.parseLong(request.getAttribute("userId").toString());
        Airline airline = airlineService.getAirlineByUserId(userId);
        // Airline airline = airlineService.getAirlineById(flightDTO.getAirlineId());
        if (airline == null) {
            return Response.error("Airline not found");
        }

        flightService.addFlight(flightDTO);
        return Response.success("Flight added successfully");
    }

    @PutMapping("/{id}")
    public Response<String> updateFlight(@PathVariable(name = "id") long id, @RequestBody FlightDTO flightDTO,
                                         HttpServletRequest request) {
        Long userId = Long.parseLong(request.getAttribute("userId").toString());
        Airline airline = airlineService.getAirlineByUserId(userId);
        // Airline airline = airlineService.getAirlineById(flightDTO.getAirlineId());
        Flight flight = flightService.getFlightById(id);
        if (flight == null) {
            return Response.error("Flight not found");
        }

        // Airline airline = airlineService.getAirlineById(flightDTO.getAirlineId());
        if (airline == null) {
            return Response.error("Airline not found");
        }

        flightService.updateFlight(flight, flightDTO);
        return Response.success("Flight updated successfully");
    }

    @DeleteMapping("/{id}")
    public Response<String> deleteFlight(@PathVariable(name = "id") long id) {
        Flight flight = flightService.getFlightById(id);
        if (flight == null) {
            return Response.error("Flight not found");
        }

        flightService.deleteFlight(id);
        return Response.success("Flight deleted successfully");
    }
}
