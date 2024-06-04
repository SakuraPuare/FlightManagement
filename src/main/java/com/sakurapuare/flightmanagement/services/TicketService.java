package com.sakurapuare.flightmanagement.services;

import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.dto.TicketDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Ticket;
import jakarta.validation.Valid;

import java.util.List;

public interface TicketService {

    List<Ticket> getTicketList(PaginationDTO paginationDTO);

    Ticket getTicket(Long id);

    boolean isTicketConflict(TicketDTO ticketDTO);

    boolean addTicket(TicketDTO ticketDTO);

    boolean updateTicket(Long id, @Valid TicketDTO ticketDTO);

    boolean deleteTicket(Long id);

}