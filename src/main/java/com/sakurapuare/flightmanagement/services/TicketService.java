package com.sakurapuare.flightmanagement.services;

import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.dto.TicketDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Ticket;
import jakarta.validation.Valid;

import java.util.List;

public interface TicketService {

    List<Ticket> getTicketsByPagination(PaginationDTO paginationDTO);

    Ticket getTicketById(long id);

    boolean isTicketSoldOut(Ticket ticket);

    void addTicket(TicketDTO ticketDTO);

    void updateTicket(long id, @Valid TicketDTO ticketDTO);

    void deleteTicket(long id);

    Ticket getTicketByFlightIdAndSeatClass(long flightId, String seatClass);

    void sellTicket(Ticket ticket);

    void refundTicket(Ticket ticket);

}