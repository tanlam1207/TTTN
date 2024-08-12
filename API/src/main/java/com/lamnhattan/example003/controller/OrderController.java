package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.Order;
import com.lamnhattan.example003.service.OrderService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/orders")
public class OrderController {
    
    private OrderService OrderService;

    // Create Order rest API
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order Order) {
        Order savedOrder = OrderService.createOrder(Order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/Order/1
    @GetMapping("{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long OrderId) {
        Order Order = OrderService.getOrderById(OrderId);
        return new ResponseEntity<>(Order, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/Order
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> Orders = OrderService.getAllOrders();
        return new ResponseEntity<>(Orders, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/Order/1
    @PutMapping("{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Long OrderId,
            @RequestBody Order Order) {
        Order.setId(OrderId);
        Order updateOrder = OrderService.updateOrder(Order);
        return new ResponseEntity<>(updateOrder, HttpStatus.OK);
    }

    // Delete Order REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long OrderId) {
        OrderService.deleteOrder(OrderId);
        return new ResponseEntity<>("Order successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}