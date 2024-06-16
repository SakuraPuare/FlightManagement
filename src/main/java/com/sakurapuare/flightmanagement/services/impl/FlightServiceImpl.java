package com.sakurapuare.flightmanagement.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakurapuare.flightmanagement.mapper.FlightMapper;
import com.sakurapuare.flightmanagement.pojo.dto.FlightDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Flight;
import com.sakurapuare.flightmanagement.services.FlightService;
import org.springframework.beans.BeanUtils;
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
    public Flight getFlightById(long id) {
        return flightMapper.selectById(id);
    }

    @Override
    public List<Flight> getFlightsByPagination(int page, int count) {
        Page<Flight> pagination = new Page<>(page, count);
        return flightMapper.selectPage(pagination, null).getRecords();
    }

    @Override
    public List<Flight> search(String query) {
        Set<Flight> flights = new HashSet<>();
        flights.addAll(flightMapper.selectList(new QueryWrapper<Flight>().like("flight_number", query)));
        flights.addAll(flightMapper.selectList(new QueryWrapper<Flight>().like("departure_city", query)));
        flights.addAll(flightMapper.selectList(new QueryWrapper<Flight>().like("arrival_city", query)));
        return List.copyOf(flights);
    }

    @Override
    public void addFlight(FlightDTO flightDTO) {
        Flight flight = new Flight();
        BeanUtils.copyProperties(flightDTO, flight);
        flightMapper.insert(flight);

    }

    @Override
    public void updateFlight(Flight flight, FlightDTO flightDTO) {
        BeanUtils.copyProperties(flightDTO, flight);
        flightMapper.updateById(flight);
    }

    @Override
    public void deleteFlight(long id) {
        flightMapper.deleteById(id);
    }

    @Override
    public long count() {
        return flightMapper.selectCount(null);
    }

}
