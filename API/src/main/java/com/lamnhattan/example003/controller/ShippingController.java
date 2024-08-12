package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.Shiping;
import com.lamnhattan.example003.service.ShippingService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/shippings")
public class ShippingController {
    
    private ShippingService ShippingService;

    // Create Shipping rest API
    @PostMapping
    public ResponseEntity<Shiping> createShipping(@RequestBody Shiping Shipping) {
        Shiping savedShipping = ShippingService.createShipping(Shipping);
        return new ResponseEntity<>(savedShipping, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/Shipping/1
    @GetMapping("{id}")
    public ResponseEntity<Shiping> getShippingById(@PathVariable("id") Long ShippingId) {
        Shiping Shipping = ShippingService.getShippingById(ShippingId);
        return new ResponseEntity<>(Shipping, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/Shipping
    @GetMapping
    public ResponseEntity<List<Shiping>> getAllShippings() {
        List<Shiping> Shippings = ShippingService.getAllShippings();
        return new ResponseEntity<>(Shippings, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/Shipping/1
    @PutMapping("{id}")
    public ResponseEntity<Shiping> updateShipping(@PathVariable("id") Long ShippingId,
            @RequestBody Shiping Shipping) {
        Shipping.setId(ShippingId);
        Shiping updateShipping = ShippingService.updateShipping(Shipping);
        return new ResponseEntity<>(updateShipping, HttpStatus.OK);
    }

    // Delete Shipping REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteShipping(@PathVariable("id") Long ShippingId) {
        ShippingService.deleteShipping(ShippingId);
        return new ResponseEntity<>("Shipping successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}