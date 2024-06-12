package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.dto.TicketDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Ticket;
import com.sakurapuare.flightmanagement.services.TicketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/tickets")
@Tag(name = "Ticket Controller", description = "Ticket API Endpoints")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/list")
    public Response<List<Ticket>> getTicketList(@Valid @RequestBody PaginationDTO paginationDTO) {
        return Response.success(ticketService.getTicketsByPagination(paginationDTO));
    }

    @GetMapping("/{id}")
    public Response<Ticket> getTicket(@PathVariable("id") long id) {
        return Response.success(ticketService.getTicketById(id));
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

        if (ticketService.getTicketByFlightIdAndSeatClass(ticketDTO.getFlightId(), ticketDTO.getSeatClass()) != null) {
            return Response.error("Ticket already exists");
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
