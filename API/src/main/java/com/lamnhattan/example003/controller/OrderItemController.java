package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.Order_item;
import com.lamnhattan.example003.service.OrderItemService;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/orderItems")
public class OrderItemController {
    
    private OrderItemService OrderItemService;

    // Create OrderItem rest API
    @PostMapping
    public ResponseEntity<Order_item> createOrderItem(@RequestBody Order_item OrderItem) {
        Order_item savedOrderItem = OrderItemService.createOrderItem(OrderItem);
        return new ResponseEntity<>(savedOrderItem, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/OrderItem/1
    @GetMapping("{id}")
    public ResponseEntity<Order_item> getOrderItemById(@PathVariable("id") Long OrderItemId) {
        Order_item OrderItem = OrderItemService.getOrderItemById(OrderItemId);
        return new ResponseEntity<>(OrderItem, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/OrderItem
    @GetMapping
    public ResponseEntity<List<Order_item>> getAllOrderItems() {
        List<Order_item> OrderItems = OrderItemService.getAllOrderItems();
        return new ResponseEntity<>(OrderItems, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/OrderItem/1
    @PutMapping("{id}")
    public ResponseEntity<Order_item> updateOrderItem(@PathVariable("id") Long OrderItemId,
            @RequestBody Order_item OrderItem) {
        OrderItem.setId(OrderItemId);
        Order_item updateOrderItem = OrderItemService.updateOrderItem(OrderItem);
        return new ResponseEntity<>(updateOrderItem, HttpStatus.OK);
    }

    // Delete OrderItem REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable("id") Long OrderItemId) {
        OrderItemService.deleteOrderItem(OrderItemId);
        return new ResponseEntity<>("OrderItem successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}