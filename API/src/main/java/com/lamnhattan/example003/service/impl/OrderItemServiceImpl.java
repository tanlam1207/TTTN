package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Order_item;
import com.lamnhattan.example003.repository.OrderItemRepository;
import com.lamnhattan.example003.service.OrderItemService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemRepository OrderItemRepository;

    @Override
    public Order_item createOrderItem(Order_item OrderItem) {
        return OrderItemRepository.save(OrderItem);
    }

    @Override
    public Order_item getOrderItemById(Long OrderItemId) {
        Optional<Order_item> optionalOrderItem = OrderItemRepository.findById(OrderItemId);
        return optionalOrderItem.get();
    }

    @Override
    public List<Order_item> getAllOrderItems() {
        return OrderItemRepository.findAll();
    }

    @Override
    public Order_item updateOrderItem(Order_item OrderItem) {
        Order_item existingOrderItem = OrderItemRepository.findById(OrderItem.getId()).get();
        existingOrderItem.setProduct_id(OrderItem.getProduct_id());
        existingOrderItem.setOrder_id(OrderItem.getOrder_id());
        existingOrderItem.setPrice(OrderItem.getPrice());
        existingOrderItem.setQuantity(OrderItem.getQuantity());
        Order_item updatedOrderItem = OrderItemRepository.save(existingOrderItem);
        return updatedOrderItem;
    }

    @Override
    public void deleteOrderItem(Long OrderItemId) {
        OrderItemRepository.deleteById(OrderItemId);
    }

}
