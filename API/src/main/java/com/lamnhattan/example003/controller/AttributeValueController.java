package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.AttributeValue;
import com.lamnhattan.example003.service.AttributeValueService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/attributeValues")
public class AttributeValueController {
    
    private AttributeValueService AttributeValueService;

    // Create AttributeValue rest API
    @PostMapping
    public ResponseEntity<AttributeValue> createAttributeValue(@RequestBody AttributeValue AttributeValue) {
        AttributeValue savedAttributeValue = AttributeValueService.createAttributeValue(AttributeValue);
        return new ResponseEntity<>(savedAttributeValue, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/AttributeValue/1
    @GetMapping("{id}")
    public ResponseEntity<AttributeValue> getAttributeValueById(@PathVariable("id") Long AttributeValueId) {
        AttributeValue AttributeValue = AttributeValueService.getAttributeValueById(AttributeValueId);
        return new ResponseEntity<>(AttributeValue, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/AttributeValue
    @GetMapping
    public ResponseEntity<List<AttributeValue>> getAllAttributeValues() {
        List<AttributeValue> AttributeValues = AttributeValueService.getAllAttributeValues();
        return new ResponseEntity<>(AttributeValues, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/AttributeValue/1
    @PutMapping("{id}")
    public ResponseEntity<AttributeValue> updateAttributeValue(@PathVariable("id") Long AttributeValueId,
            @RequestBody AttributeValue AttributeValue) {
        AttributeValue.setId(AttributeValueId);
        AttributeValue updateAttributeValue = AttributeValueService.updateAttributeValue(AttributeValue);
        return new ResponseEntity<>(updateAttributeValue, HttpStatus.OK);
    }

    // Delete AttributeValue REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAttributeValue(@PathVariable("id") Long AttributeValueId) {
        AttributeValueService.deleteAttributeValue(AttributeValueId);
        return new ResponseEntity<>("AttributeValue successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}