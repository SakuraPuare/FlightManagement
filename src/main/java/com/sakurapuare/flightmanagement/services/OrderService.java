package com.sakurapuare.flightmanagement.services;

import com.sakurapuare.flightmanagement.pojo.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrdersByPaginationAndUserId(int page, int count, long userId);

    Order getOrderByIdAndUserId(long id, long userId);

    void addOrder(long ticketId, long userId);

    void deleteOrder(Order order);

    boolean isOrderConflict(long id, long userId);

    void payOrder(Order order);

    List<Order> getOrdersAll(long userId);

    long count();

    void cancelOrder(Order order);
}
