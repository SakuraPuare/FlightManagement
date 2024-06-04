package com.sakurapuare.flightmanagement.services;

import com.sakurapuare.flightmanagement.pojo.dto.FlightDTO;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Flight;
import jakarta.validation.Valid;

import java.util.List;

public interface FlightService {
    List<Flight> getFlightsByPagination(PaginationDTO paginationDTO);

    List<Flight> search(String query);

    Flight findFlightById(Long id);

    Flight addFlight(@Valid FlightDTO flightDTO);

    boolean updateFlight(Flight flight, FlightDTO flightDTO);

    boolean deleteFlight(Long id);

}
