package com.lamnhattan.example003.service;

import java.util.List;

import com.lamnhattan.example003.entity.Order;


public interface OrderService {
    Order createOrder(Order Order);

    Order getOrderById(Long OrderId);

    List<Order> getAllOrders();

    Order updateOrder(Order Order);

    void deleteOrder(Long OrderId);
}
