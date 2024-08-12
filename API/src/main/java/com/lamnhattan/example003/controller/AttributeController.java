package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.Attribute;
import com.lamnhattan.example003.service.AttributeService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/attributes")
public class AttributeController {
    
    private AttributeService AttributeService;

    // Create Attribute rest API
    @PostMapping
    public ResponseEntity<Attribute> createAttribute(@RequestBody Attribute Attribute) {
        Attribute savedAttribute = AttributeService.createAttribute(Attribute);
        return new ResponseEntity<>(savedAttribute, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/Attribute/1
    @GetMapping("{id}")
    public ResponseEntity<Attribute> getAttributeById(@PathVariable("id") Long AttributeId) {
        Attribute Attribute = AttributeService.getAttributeById(AttributeId);
        return new ResponseEntity<>(Attribute, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/Attribute
    @GetMapping
    public ResponseEntity<List<Attribute>> getAllAttributes() {
        List<Attribute> Attributes = AttributeService.getAllAttributes();
        return new ResponseEntity<>(Attributes, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/Attribute/1
    @PutMapping("{id}")
    public ResponseEntity<Attribute> updateAttribute(@PathVariable("id") Long AttributeId,
            @RequestBody Attribute Attribute) {
        Attribute.setId(AttributeId);
        Attribute updateAttribute = AttributeService.updateAttribute(Attribute);
        return new ResponseEntity<>(updateAttribute, HttpStatus.OK);
    }

    // Delete Attribute REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAttribute(@PathVariable("id") Long AttributeId) {
        AttributeService.deleteAttribute(AttributeId);
        return new ResponseEntity<>("Attribute successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}