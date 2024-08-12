package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.ProductShipping;
import com.lamnhattan.example003.service.ProductShippingService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/productShippings")
public class ProductShippingController {
    
    private ProductShippingService ProductShippingService;

    // Create ProductShipping rest API
    @PostMapping
    public ResponseEntity<ProductShipping> createProductShipping(@RequestBody ProductShipping ProductShipping) {
        ProductShipping savedProductShipping = ProductShippingService.createProductShipping(ProductShipping);
        return new ResponseEntity<>(savedProductShipping, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/ProductShipping/1
    @GetMapping("{id}")
    public ResponseEntity<ProductShipping> getProductShippingById(@PathVariable("id") ProductShipping.ProductShippingID ProductShippingId) {
        ProductShipping ProductShipping = ProductShippingService.getProductShippingById(ProductShippingId);
        return new ResponseEntity<>(ProductShipping, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/ProductShipping
    @GetMapping
    public ResponseEntity<List<ProductShipping>> getAllProductShippings() {
        List<ProductShipping> ProductShippings = ProductShippingService.getAllProductShippings();
        return new ResponseEntity<>(ProductShippings, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/ProductShipping/1
    @PutMapping("{id}")
    public ResponseEntity<ProductShipping> updateProductShipping(@PathVariable("id") ProductShipping.ProductShippingID ProductShippingId,
            @RequestBody ProductShipping ProductShipping) {
        ProductShipping.setId(ProductShippingId);
        ProductShipping updateProductShipping = ProductShippingService.updateProductShipping(ProductShipping);
        return new ResponseEntity<>(updateProductShipping, HttpStatus.OK);
    }

    // Delete ProductShipping REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductShipping(@PathVariable("id") ProductShipping.ProductShippingID ProductShippingId) {
        ProductShippingService.deleteProductShipping(ProductShippingId);
        return new ResponseEntity<>("ProductShipping successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}