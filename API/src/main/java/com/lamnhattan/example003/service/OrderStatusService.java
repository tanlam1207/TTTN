package com.lamnhattan.example003.service;

import java.util.List;

import com.lamnhattan.example003.entity.Order_status;


public interface OrderStatusService {
    Order_status createOrderStatus(Order_status OrderStatus);

    Order_status getOrderStatusById(Long OrderStatusId);

    List<Order_status> getAllOrderStatuss();

    Order_status updateOrderStatus(Order_status OrderStatus);

    void deleteOrderStatus(Long OrderStatusId);
}
