package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.pojo.dto.FlightDTO;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Flight;
import com.sakurapuare.flightmanagement.pojo.entity.user.Airline;
import com.sakurapuare.flightmanagement.services.FlightService;
import com.sakurapuare.flightmanagement.services.user.AirlineService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Response<List<Flight>> getFlightList(@Valid @RequestBody PaginationDTO paginationDTO) {
        return Response.success(flightService.getFlightsByPagination(paginationDTO));
    }

    @GetMapping("/{id}")
    public Response<Flight> getFlightById(@PathVariable(name = "id") long id) {
        Flight flight = flightService.findFlightById(id);
        if (flight == null) {
            return Response.error("Flight not found");
        }

        return Response.success(flight);
    }

    @GetMapping("/search")
    public Response<List<Flight>> searchFlight(@Valid @RequestBody String query) {
        return Response.success(flightService.search(query));
    }

    @PostMapping("/")
    public Response<Void> addFlight(@Valid @RequestBody FlightDTO flightDTO) {
        Airline airline = airlineService.findAirlineById(flightDTO.getAirlineId());
        if (airline == null) {
            return Response.error("Airline not found");
        }

        flightService.addFlight(flightDTO);
        return Response.success("Flight added successfully");
    }

    @PutMapping("/{id}")
    public Response<Void> updateFlight(@PathVariable(name = "id") long id, @Valid @RequestBody FlightDTO flightDTO) {
        Flight flight = flightService.findFlightById(id);
        if (flight == null) {
            return Response.error("Flight not found");
        }

        Airline airline = airlineService.findAirlineById(flightDTO.getAirlineId());
        if (airline == null) {
            return Response.error("Airline not found");
        }

        flightService.updateFlight(flight, flightDTO);
        return Response.success("Flight updated successfully");
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteFlight(@PathVariable(name = "id") long id) {
        Flight flight = flightService.findFlightById(id);
        if (flight == null) {
            return Response.error("Flight not found");
        }

        flightService.deleteFlight(id);
        return Response.success("Flight deleted successfully");
    }
}
