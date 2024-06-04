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
        return Response.success(ticketService.getTicketList(paginationDTO));
    }

    @GetMapping("/{id}")
    public Response<Ticket> getTicket(@PathVariable("id") Long id) {
        return Response.success(ticketService.getTicket(id));
    }

    @PostMapping("/")
    public Response<Void> addTicket(@Valid @RequestBody TicketDTO ticketDTO) {
        if (ticketService.isTicketConflict(ticketDTO)) {
            return Response.error("Ticket conflict");
        }
        ticketService.addTicket(ticketDTO);

        return Response.success("Ticket added successfully");
    }

    @PutMapping("/{id}")
    public Response<Void> updateTicket(@PathVariable("id") Long id, @Valid @RequestBody TicketDTO ticketDTO) {
        if (!ticketService.isTicketConflict(ticketDTO)) {
            return Response.error("Ticket conflict");
        }
        ticketService.updateTicket(id, ticketDTO);

        return Response.success("Ticket updated successfully");
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteTicket(@PathVariable("id") Long id) {
        ticketService.deleteTicket(id);

        return Response.success("Ticket deleted successfully");
    }

}
