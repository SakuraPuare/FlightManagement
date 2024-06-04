package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.dto.TicketDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Ticket;
import com.sakurapuare.flightmanagement.services.TicketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/ticket")
@Tag(name = "Ticket Controller", description = "Ticket API Endpoints")
public class TicketController {

    @Autowired
    private TicketService ticketService;

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

        if (!ticketService.isTicketConflict(ticketDTO)) {
            return Response.error("Ticket conflict");
        }

        return Response.success("Ticket added successfully");
    }
}
