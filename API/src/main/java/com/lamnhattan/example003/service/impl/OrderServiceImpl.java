package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Order;
import com.lamnhattan.example003.repository.OrderRepository;
import com.lamnhattan.example003.service.OrderService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class OrderServiceImpl implements OrderService {
    private OrderRepository OrderRepository;

    @Override
    public Order createOrder(Order Order) {
        return OrderRepository.save(Order);
    }

    @Override
    public Order getOrderById(Long OrderId) {
        Optional<Order> optionalOrder = OrderRepository.findById(OrderId);
        return optionalOrder.get();
    }

    @Override
    public List<Order> getAllOrders() {
        return OrderRepository.findAll();
    }

    @Override
    public Order updateOrder(Order Order) {
        Order existingOrder = OrderRepository.findById(Order.getId()).get();
        existingOrder.setCustomer_id(Order.getCustomer_id());
        existingOrder.setOrderStatus(Order.getOrderStatus());
        existingOrder.setOrder_delivered_carrler_date(Order.getOrder_delivered_carrler_date());
        existingOrder.setCreated_at(Order.getCreated_at());

        Order updatedOrder = OrderRepository.save(existingOrder);
        return updatedOrder;
    }

    @Override
    public void deleteOrder(Long OrderId) {
        OrderRepository.deleteById(OrderId);
    }

}
