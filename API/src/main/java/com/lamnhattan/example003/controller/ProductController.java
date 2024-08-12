package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.Category;
import com.lamnhattan.example003.entity.Product;
import com.lamnhattan.example003.service.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/products")

@CrossOrigin(origins = "*")
public class ProductController {
    private ProductService ProductService;

    // Get All Products REST API with Pagination
    // Create Product REST API
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product Product) {
        Product savedProduct = ProductService.createProduct(Product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    // Get ALL Products REST API
    // http://localhost:8000/api/Products/1
    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") UUID ProductId) {
        Product Product = ProductService.getProductById(ProductId);
        return new ResponseEntity<>(Product, HttpStatus.OK);
    }

    // Get ALL Products REST API
    // http://localhost:8000/api/Products
    // @GetMapping
    // public ResponseEntity<List<Product>> getAllProducts() {
    //     List<Product> Products = ProductService.getAllProducts();
    //     return new ResponseEntity<>(Products, HttpStatus.OK);
    // }

    // Get All Products REST API with Pagination
    // http://localhost:8080/api/Products?page=0&size=10
    @GetMapping
    public ResponseEntity<List<Product>>getAllProducts () {
        List<Product> products = ProductService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    // Update Product REST API
    @PutMapping("{id}")
    // http://localhost:8000/api/Products/1
    public ResponseEntity<Product> updateProduct(@PathVariable("id") UUID ProductId, @RequestBody Product Product) {
        Product.setProduct_id(ProductId);
        Product updatedProduct = ProductService.updateProduct(Product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    // Delete Product REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") UUID ProductId) {
        ProductService.deleteProduct(ProductId);
        return new ResponseEntity<>("Product successfully deleted!", HttpStatus.OK);
    }
    
    

}