package com.sakurapuare.flightmanagement.services;

import com.sakurapuare.flightmanagement.pojo.dto.FlightDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Flight;

import java.util.List;

public interface FlightService {
    Flight getFlightById(long id);

    List<Flight> getFlightsByPagination(int page, int count);

    List<Flight> search(String query);

    void addFlight(FlightDTO flightDTO);

    void updateFlight(Flight flight, FlightDTO flightDTO);

    void deleteFlight(long id);

    long count();

    List<Flight> getFlightsByPaginationAndUserId(int page, int count, Long userId);

}
