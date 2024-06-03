package com.sakurapuare.flightmanagement.services;

import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Flight;

import java.util.List;

public interface FlightService {
    List<Flight> getFlightsByPagination(PaginationDTO paginationDTO);

    List<Flight> search(String query);
}
