package com.lamnhattan.example003.service;

import java.util.List;

import com.lamnhattan.example003.entity.Order_item;


public interface OrderItemService {
    Order_item createOrderItem(Order_item OrderItem);

    Order_item getOrderItemById(Long OrderItemId);

    List<Order_item> getAllOrderItems();

    Order_item updateOrderItem(Order_item OrderItem);

    void deleteOrderItem(Long OrderItemId);
}
