package com.sakurapuare.flightmanagement.services;

import com.sakurapuare.flightmanagement.pojo.dto.FlightDTO;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Flight;

import java.util.List;

public interface FlightService {
    Flight getFlightById(long id);

    List<Flight> getFlightsByPagination(PaginationDTO paginationDTO);

    List<Flight> search(String query);

    void addFlight(FlightDTO flightDTO);

    void updateFlight(Flight flight, FlightDTO flightDTO);

    void deleteFlight(long id);

}
