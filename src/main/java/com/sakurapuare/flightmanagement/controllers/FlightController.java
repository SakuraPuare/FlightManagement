package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.mapper.FlightMapper;
import com.sakurapuare.flightmanagement.mapper.user.AirlineMapper;
import com.sakurapuare.flightmanagement.pojo.dto.FlightDTO;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Flight;
import com.sakurapuare.flightmanagement.pojo.entity.user.Airline;
import com.sakurapuare.flightmanagement.services.FlightService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    private final FlightMapper flightMapper;

    private final AirlineMapper airlineMapper;

    public FlightController(FlightService flightService, FlightMapper flightMapper, AirlineMapper airlineMapper) {
        this.flightService = flightService;
        this.flightMapper = flightMapper;
        this.airlineMapper = airlineMapper;
    }

    @GetMapping("/list")
    public Response<List<Flight>> getFlightList(@Valid @RequestBody PaginationDTO paginationDTO) {
        return Response.success(flightService.getFlightsByPagination(paginationDTO));
    }

    @GetMapping("/{id}")
    public Response<Flight> getFlightById(@PathVariable(name = "id") Long id) {
        Flight flight = flightMapper.selectById(id);
        if (flight == null) {
            return Response.error("Flight not found");
        }

        return Response.success(flightMapper.selectById(id));
    }

    @GetMapping("/search")
    public Response<List<Flight>> searchFlight(@Valid @RequestBody String query) {
        return Response.success(flightService.search(query));
    }

    @PostMapping("/")
    public Response<Void> addFlight(@Valid @RequestBody FlightDTO flightDTO) {
        Airline airline = airlineMapper.selectById(flightDTO.getAirlineId());
        if (airline == null) {
            return Response.error("Airline not found");
        }

        Flight flight = new Flight();
        BeanUtils.copyProperties(flightDTO, flight);
        flightMapper.insert(flight);
        return Response.success("Flight added successfully");
    }

    @PutMapping("/{id}")
    public Response<Void> updateFlight(@PathVariable(name = "id") Long id, @Valid @RequestBody FlightDTO flightDTO) {
        Flight flight = flightMapper.selectById(id);
        if (flight == null) {
            return Response.error("Flight not found");
        }

        Airline airline = airlineMapper.selectById(flightDTO.getAirlineId());
        if (airline == null) {
            return Response.error("Airline not found");
        }

        BeanUtils.copyProperties(flightDTO, flight);
        flightMapper.updateById(flight);
        return Response.success("Flight updated successfully");
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteFlight(@PathVariable(name = "id") Long id) {
        Flight flight = flightMapper.selectById(id);
        if (flight == null) {
            return Response.error("Flight not found");
        }

        flightMapper.deleteById(id);
        return Response.success("Flight deleted successfully");
    }
}
