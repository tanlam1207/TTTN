package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.ProductAttribute;
import com.lamnhattan.example003.service.ProductAttributeService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/ProductAttributes")
public class ProductAttributeController {
    
    private ProductAttributeService ProductAttributeService;

    // Create ProductAttribute rest API
    @PostMapping
    public ResponseEntity<ProductAttribute> createProductAttribute(@RequestBody ProductAttribute ProductAttribute) {
        ProductAttribute savedProductAttribute = ProductAttributeService.createProductAttribute(ProductAttribute);
        return new ResponseEntity<>(savedProductAttribute, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/ProductAttribute/1
    @GetMapping("{id}")
    public ResponseEntity<ProductAttribute> getProductAttributeById(@PathVariable("id") ProductAttribute.ProductAttributeID ProductAttributeId) {
        ProductAttribute ProductAttribute = ProductAttributeService.getProductAttributeById(ProductAttributeId);
        return new ResponseEntity<>(ProductAttribute, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/ProductAttribute
    @GetMapping
    public ResponseEntity<List<ProductAttribute>> getAllProductAttributes() {
        List<ProductAttribute> ProductAttributes = ProductAttributeService.getAllProductAttributes();
        return new ResponseEntity<>(ProductAttributes, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/ProductAttribute/1
    @PutMapping("{id}")
    public ResponseEntity<ProductAttribute> updateProductAttribute(@PathVariable("id") ProductAttribute.ProductAttributeID ProductAttributeId,
            @RequestBody ProductAttribute ProductAttribute) {
        ProductAttribute.setId(ProductAttributeId);
        ProductAttribute updateProductAttribute = ProductAttributeService.updateProductAttribute(ProductAttribute);
        return new ResponseEntity<>(updateProductAttribute, HttpStatus.OK);
    }

    // Delete ProductAttribute REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductAttribute(@PathVariable("id") ProductAttribute.ProductAttributeID ProductAttributeId) {
        ProductAttributeService.deleteProductAttribute(ProductAttributeId);
        return new ResponseEntity<>("ProductAttribute successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}