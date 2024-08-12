package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.Product_tags;
import com.lamnhattan.example003.service.ProductTagService;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")@RestController
@AllArgsConstructor
@RequestMapping("/productTags")
public class ProductTagController {
    
    private ProductTagService ProductTagService;

    // Create ProductTag rest API
    @PostMapping
    public ResponseEntity<Product_tags> createProductTag(@RequestBody Product_tags ProductTag) {
        Product_tags savedProductTag = ProductTagService.createProductTag(ProductTag);
        return new ResponseEntity<>(savedProductTag, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/ProductTag/1
    @GetMapping("{id}")
    public ResponseEntity<Product_tags> getProductTagById(@PathVariable("id") UUID ProductTagId) {
        Product_tags ProductTag = ProductTagService.getProductTagById(ProductTagId);
        return new ResponseEntity<>(ProductTag, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/ProductTag
    @GetMapping
    public ResponseEntity<List<Product_tags>> getAllProductTags() {
        List<Product_tags> ProductTags = ProductTagService.getAllProductTags();
        return new ResponseEntity<>(ProductTags, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/ProductTag/1
    @PutMapping("{id}")
    public ResponseEntity<Product_tags> updateProductTag(@PathVariable("id") UUID ProductTagId,
            @RequestBody Product_tags ProductTag) {
        ProductTag.setId(ProductTagId);
        Product_tags updateProductTag = ProductTagService.updateProductTag(ProductTag);
        return new ResponseEntity<>(updateProductTag, HttpStatus.OK);
    }

    // Delete ProductTag REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductTag(@PathVariable("id") UUID ProductTagId) {
        ProductTagService.deleteProductTag(ProductTagId);
        return new ResponseEntity<>("ProductTag successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}