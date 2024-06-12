package com.sakurapuare.flightmanagement.services;

import com.sakurapuare.flightmanagement.pojo.dto.PaginationDTO;
import com.sakurapuare.flightmanagement.pojo.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrdersByPaginationAndUserId(PaginationDTO paginationDTO, long userId);

    Order getOrderByIdAndUserId(long id, long userId);

    void addOrder(long ticketId, long userId);

    void deleteOrder(Order order);

    boolean isOrderConflict(long id, long userId);

    void payOrder(Order order);
}
