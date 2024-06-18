package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.pojo.dto.FlightDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Flight;
import com.sakurapuare.flightmanagement.pojo.entity.user.Airline;
import com.sakurapuare.flightmanagement.pojo.vo.FlightVO;
import com.sakurapuare.flightmanagement.services.FlightService;
import com.sakurapuare.flightmanagement.services.user.AirlineService;
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

    @GetMapping("/{id}")
    public Response<FlightVO> getFlightById(@PathVariable(name = "id") long id) {
        Flight flight = flightService.getFlightById(id);
        if (flight == null) {
            return Response.error("Flight not found");
        }

        Airline airline = airlineService.getAirlineById(flight.getAirlineId());
        FlightVO flightVO = new FlightVO();
        BeanUtils.copyProperties(flight, flightVO);
        BeanUtils.copyProperties(airline, flightVO);

        return Response.success(flightVO);
    }

    @GetMapping("/search")
    public Response<List<FlightVO>> searchFlight(@Valid @RequestBody String query) {
        List<Flight> flightList = flightService.search(query);
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

    @PostMapping("/")
    public Response<Void> addFlight(@Valid @RequestBody FlightDTO flightDTO) {
        Airline airline = airlineService.getAirlineById(flightDTO.getAirlineId());
        if (airline == null) {
            return Response.error("Airline not found");
        }

        flightService.addFlight(flightDTO);
        return Response.success("Flight added successfully");
    }

    @PutMapping("/{id}")
    public Response<Void> updateFlight(@PathVariable(name = "id") long id, @RequestBody FlightDTO flightDTO) {
        Flight flight = flightService.getFlightById(id);
        if (flight == null) {
            return Response.error("Flight not found");
        }

        Airline airline = airlineService.getAirlineById(flightDTO.getAirlineId());
        if (airline == null) {
            return Response.error("Airline not found");
        }

        flightService.updateFlight(flight, flightDTO);
        return Response.success("Flight updated successfully");
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteFlight(@PathVariable(name = "id") long id) {
        Flight flight = flightService.getFlightById(id);
        if (flight == null) {
            return Response.error("Flight not found");
        }

        flightService.deleteFlight(id);
        return Response.success("Flight deleted successfully");
    }
}
