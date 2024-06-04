package com.sakurapuare.flightmanagement.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakurapuare.flightmanagement.mapper.FlightMapper;
import com.sakurapuare.flightmanagement.mapper.TicketMapper;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.dto.TicketDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Flight;
import com.sakurapuare.flightmanagement.pojo.entity.Ticket;
import com.sakurapuare.flightmanagement.services.TicketService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketMapper ticketMapper;

    private final FlightMapper flightMapper;

    public TicketServiceImpl(TicketMapper ticketMapper, FlightMapper flightMapper) {
        this.ticketMapper = ticketMapper;
        this.flightMapper = flightMapper;
    }

    @Override
    public List<Ticket> getTicketList(PaginationDTO paginationDTO) {
        Page<Ticket> page = new Page<>(paginationDTO.getPage(), paginationDTO.getCount());
        return ticketMapper.selectPage(page, null).getRecords();
    }

    @Override
    public Ticket getTicket(Long id) {
        return ticketMapper.selectById(id);
    }

    @Override
    public boolean isTicketConflict(TicketDTO ticketDTO) {
        // get person's ticket list
        List<Ticket> ticketList = ticketMapper
                .selectList(new QueryWrapper<Ticket>()
                        .eq("passenger_id", ticketDTO.getPassengerId()));

        if (ticketList.isEmpty()) {
            return false;
        }

        // check if the person has bought the same flight
        for (Ticket ticket : ticketList) {
            if (ticket.getFlightId().equals(ticketDTO.getFlightId())) {
                return true;
            }
        }

        // find the flight
        Flight flight = flightMapper.selectById(ticketDTO.getFlightId());
        // check if you have a conflict time
        for (Ticket ticket : ticketList) {
            LocalDateTime newDepartureTime = flight.getDateOfDeparture();
            Flight flight1 = flightMapper.selectById(ticket.getFlightId());
            LocalDateTime departureTime = flight1.getDateOfDeparture();
            LocalDateTime arrivalTime = flight1.getDateOfDeparture().plusMinutes(flight1.getEstimatedTravelTime());
            if (newDepartureTime.isAfter(departureTime) && newDepartureTime.isBefore(arrivalTime)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addTicket(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketDTO, ticket);
        return ticketMapper.insert(ticket) > 0;
    }

    @Override
    public boolean updateTicket(Long id, TicketDTO ticketDTO) {
        Ticket ticket = getTicket(id);
        if (ticket == null) {
            return false;
        }
        return ticketMapper.updateById(ticket) > 0;
    }

    @Override
    public boolean deleteTicket(Long id) {
        Ticket ticket = getTicket(id);
        if (ticket == null) {
            return false;
        }
        return ticketMapper.deleteById(id) > 0;
    }
}
