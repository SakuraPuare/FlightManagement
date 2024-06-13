package com.sakurapuare.flightmanagement.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakurapuare.flightmanagement.mapper.TicketMapper;
import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.dto.TicketDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Ticket;
import com.sakurapuare.flightmanagement.services.TicketService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketMapper ticketMapper;

    public TicketServiceImpl(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    @Override
    public List<Ticket> getTicketsByPagination(PaginationDTO paginationDTO) {
        Page<Ticket> page = new Page<>(paginationDTO.getPage(), paginationDTO.getCount());
        return ticketMapper.selectPage(page, null).getRecords();
    }

    @Override
    public Ticket getTicketById(long id) {
        return ticketMapper.selectById(id);
    }

    @Override
    public Ticket getTicketByFlightIdAndSeatClass(long flightId, String seatClass) {
        return ticketMapper.selectOne(
                new QueryWrapper<Ticket>()
                        .eq("flight_id", flightId)
                        .eq("seat_class", seatClass));
    }

    @Override
    public boolean isTicketSoldOut(Ticket ticket) {
        return ticket.getQuota() <= 0;
    }

    @Override
    public void addTicket(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketDTO, ticket);
        ticketMapper.insert(ticket);
    }

    @Override
    public void updateTicket(long id, TicketDTO ticketDTO) {
        Ticket ticket = getTicketById(id);
        if (ticket == null) {
            return;
        }
        BeanUtils.copyProperties(ticketDTO, ticket);
        ticketMapper.updateById(ticket);
    }

    @Override
    public void deleteTicket(long id) {
        ticketMapper.deleteById(id);
    }

    @Override
    public void sellTicket(Ticket ticket) {
        ticket.setQuota(ticket.getQuota() - 1);
        ticketMapper.updateById(ticket);
    }

    @Override
    public void refundTicket(Ticket ticket) {
        ticket.setQuota(ticket.getQuota() + 1);
        ticketMapper.updateById(ticket);
    }

    @Override
    public long count() {
        return ticketMapper.selectCount(null);
    }

}
