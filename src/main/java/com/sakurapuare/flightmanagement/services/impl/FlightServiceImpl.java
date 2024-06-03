package com.sakurapuare.flightmanagement.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakurapuare.flightmanagement.mapper.FlightMapper;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Flight;
import com.sakurapuare.flightmanagement.services.FlightService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightMapper flightMapper;

    public FlightServiceImpl(FlightMapper flightMapper) {
        this.flightMapper = flightMapper;
    }

    @Override
    public List<Flight> getFlightsByPagination(PaginationDTO paginationDTO) {
        Page<Flight> page = new Page<>(paginationDTO.getPage(), paginationDTO.getCount());
        return flightMapper.selectPage(page, null).getRecords();
    }

    @Override
    public List<Flight> search(String query) {
        Set<Flight> flights = new HashSet<>();
        flights.addAll(flightMapper.selectList(new QueryWrapper<Flight>().like("flight_number", query)));
        flights.addAll(flightMapper.selectList(new QueryWrapper<Flight>().like("departure_city", query)));
        flights.addAll(flightMapper.selectList(new QueryWrapper<Flight>().like("arrival_city", query)));
        return List.copyOf(flights);
    }
}
