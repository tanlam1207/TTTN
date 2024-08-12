package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Order_status;
import com.lamnhattan.example003.repository.OrderStatusRepository;
import com.lamnhattan.example003.service.OrderStatusService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class OrderStatusServiceImpl implements OrderStatusService {
    private OrderStatusRepository OrderStatusRepository;

    @Override
    public Order_status createOrderStatus(Order_status OrderStatus) {
        return OrderStatusRepository.save(OrderStatus);
    }

    @Override
    public Order_status getOrderStatusById(Long OrderStatusId) {
        Optional<Order_status> optionalOrderStatus = OrderStatusRepository.findById(OrderStatusId);
        return optionalOrderStatus.get();
    }

    @Override
    public List<Order_status> getAllOrderStatuss() {
        return OrderStatusRepository.findAll();
    }

    @Override
    public Order_status updateOrderStatus(Order_status OrderStatus) {
        Order_status existingOrderStatus = OrderStatusRepository.findById(OrderStatus.getId()).get();
        existingOrderStatus.setStatus_name(OrderStatus.getStatus_name());
        existingOrderStatus.setColor(OrderStatus.getColor());
        existingOrderStatus.setPrivacy(OrderStatus.getPrivacy());
        existingOrderStatus.setCreated_at(OrderStatus.getCreated_at());
        existingOrderStatus.setUpdated_at(OrderStatus.getUpdated_at());
        existingOrderStatus.setCreated_by(OrderStatus.getCreated_by());
        existingOrderStatus.setUpdated_by(OrderStatus.getUpdated_by());
        Order_status updatedOrderStatus = OrderStatusRepository.save(existingOrderStatus);
        return updatedOrderStatus;
    }

    @Override
    public void deleteOrderStatus(Long OrderStatusId) {
        OrderStatusRepository.deleteById(OrderStatusId);
    }

}
