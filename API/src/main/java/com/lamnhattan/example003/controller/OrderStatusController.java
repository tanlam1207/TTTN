package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.Order_status;
import com.lamnhattan.example003.service.OrderStatusService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/orderStatuss")
public class OrderStatusController {
    
    private OrderStatusService OrderStatusService;

    // Create OrderStatus rest API
    @PostMapping
    public ResponseEntity<Order_status> createOrderStatus(@RequestBody Order_status OrderStatus) {
        Order_status savedOrderStatus = OrderStatusService.createOrderStatus(OrderStatus);
        return new ResponseEntity<>(savedOrderStatus, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/OrderStatus/1
    @GetMapping("{id}")
    public ResponseEntity<Order_status> getOrderStatusById(@PathVariable("id") Long OrderStatusId) {
        Order_status OrderStatus = OrderStatusService.getOrderStatusById(OrderStatusId);
        return new ResponseEntity<>(OrderStatus, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/OrderStatus
    @GetMapping
    public ResponseEntity<List<Order_status>> getAllOrderStatuss() {
        List<Order_status> OrderStatuss = OrderStatusService.getAllOrderStatuss();
        return new ResponseEntity<>(OrderStatuss, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/OrderStatus/1
    @PutMapping("{id}")
    public ResponseEntity<Order_status> updateOrderStatus(@PathVariable("id") Long OrderStatusId,
            @RequestBody Order_status OrderStatus) {
        OrderStatus.setId(OrderStatusId);
        Order_status updateOrderStatus = OrderStatusService.updateOrderStatus(OrderStatus);
        return new ResponseEntity<>(updateOrderStatus, HttpStatus.OK);
    }

    // Delete OrderStatus REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrderStatus(@PathVariable("id") Long OrderStatusId) {
        OrderStatusService.deleteOrderStatus(OrderStatusId);
        return new ResponseEntity<>("OrderStatus successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}