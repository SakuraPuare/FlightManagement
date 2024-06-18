package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.pojo.dto.TicketDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Flight;
import com.sakurapuare.flightmanagement.pojo.entity.Ticket;
import com.sakurapuare.flightmanagement.pojo.entity.user.Airline;
import com.sakurapuare.flightmanagement.pojo.vo.TicketVO;
import com.sakurapuare.flightmanagement.services.FlightService;
import com.sakurapuare.flightmanagement.services.TicketService;
import com.sakurapuare.flightmanagement.services.user.AirlineService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/tickets")
@Tag(name = "Ticket Controller", description = "Ticket API Endpoints")
public class TicketController {

    private final TicketService ticketService;

    private final AirlineService airlineService;

    private final FlightService flightService;

    public TicketController(TicketService ticketService, AirlineService airlineService, FlightService flightService) {
        this.ticketService = ticketService;
        this.airlineService = airlineService;
        this.flightService = flightService;
    }

    @GetMapping("/my")
    public Response<List<TicketVO>> getMyTicketList(@RequestParam("page") int page, @RequestParam("count") int count,
            HttpServletRequest request) {
        Long userId = Long.parseLong(request.getAttribute("userId").toString());

        List<Ticket> ticketList = ticketService.getTicketsByPaginationAndUserId(page, count, userId);
        List<TicketVO> ticketVOList = new ArrayList<>();
        Map<Long, Airline> airlineMap = new HashMap<>();
        Map<Long, Flight> flightMap = new HashMap<>();
        for (Ticket ticket : ticketList) {
            Flight flight = flightMap.computeIfAbsent(ticket.getFlightId(),
                    id -> flightService.getFlightById(id));
            Airline airline = airlineMap.computeIfAbsent(flight.getAirlineId(),
                    id -> airlineService.getAirlineById(id));

            TicketVO ticketVO = new TicketVO();
            BeanUtils.copyProperties(airline, ticketVO);
            BeanUtils.copyProperties(flight, ticketVO);
            BeanUtils.copyProperties(ticket, ticketVO);
            ticketVOList.add(ticketVO);
        }
        return Response.success(ticketVOList);
    }

    @GetMapping("/list")
    public Response<List<TicketVO>> getTicketList(@RequestParam("page") int page, @RequestParam("count") int count) {
        List<Ticket> ticketList = ticketService.getTicketsByPagination(page, count);
        List<TicketVO> ticketVOList = new ArrayList<>();
        Map<Long, Airline> airlineMap = new HashMap<>();
        Map<Long, Flight> flightMap = new HashMap<>();
        for (Ticket ticket : ticketList) {
            Flight flight = flightMap.computeIfAbsent(ticket.getFlightId(),
                    id -> flightService.getFlightById(id));
            Airline airline = airlineMap.computeIfAbsent(flight.getAirlineId(),
                    id -> airlineService.getAirlineById(id));

            TicketVO ticketVO = new TicketVO();
            BeanUtils.copyProperties(airline, ticketVO);
            BeanUtils.copyProperties(flight, ticketVO);
            BeanUtils.copyProperties(ticket, ticketVO);
            ticketVOList.add(ticketVO);
        }
        return Response.success(ticketVOList);
    }

    @GetMapping("/{id}")
    public Response<TicketVO> getTicket(@PathVariable("id") long id) {
        Ticket ticket = ticketService.getTicketById(id);
        Flight flight = flightService.getFlightById(ticket.getFlightId());
        Airline airline = airlineService.getAirlineById(flight.getAirlineId());

        TicketVO ticketVO = new TicketVO();
        BeanUtils.copyProperties(airline, ticketVO);
        BeanUtils.copyProperties(flight, ticketVO);
        BeanUtils.copyProperties(ticket, ticketVO);
        return Response.success(ticketVO);
    }

    @PostMapping("/")
    public Response<Void> addTicket(@Valid @RequestBody TicketDTO ticketDTO) {
        Ticket ticket = ticketService.getTicketByFlightIdAndSeatClass(ticketDTO.getFlightId(),
                ticketDTO.getSeatClass());
        if (ticket != null) {
            return Response.error("Ticket already exists");
        }
        ticketService.addTicket(ticketDTO);

        return Response.success("Ticket added successfully");
    }

    @PutMapping("/{id}")
    public Response<Void> updateTicket(@PathVariable("id") long id, @Valid @RequestBody TicketDTO ticketDTO) {
        Ticket ticket = ticketService.getTicketById(id);
        if (ticket == null) {
            return Response.error("Ticket not found");
        }

        ticketService.updateTicket(id, ticketDTO);

        return Response.success("Ticket updated successfully");
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteTicket(@PathVariable("id") long id) {
        ticketService.deleteTicket(id);

        return Response.success("Ticket deleted successfully");
    }

}
