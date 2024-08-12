package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.ProductCategories;
// import com.lamnhattan.example003.entity.Product_categories;
import com.lamnhattan.example003.service.ProductCategoryService;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")@RestController
@AllArgsConstructor
@RequestMapping("/ProductCategorys")
public class ProductCategoryController {
    
    private ProductCategoryService ProductCategoryService;

    // Create ProductCategory rest API
    @PostMapping
    public ResponseEntity<ProductCategories> createProductCategory(@RequestBody ProductCategories ProductCategory) {
        ProductCategories savedProductCategory = ProductCategoryService.createProductCategory(ProductCategory);
        return new ResponseEntity<>(savedProductCategory, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/ProductCategory/1
    @GetMapping("{id}")
    public ResponseEntity<ProductCategories> getProductCategoryById(@PathVariable("id") UUID ProductCategoryId) {
        ProductCategories ProductCategory = ProductCategoryService.getProductCategoryById(ProductCategoryId);
        return new ResponseEntity<>(ProductCategory, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/ProductCategory
    @GetMapping
    public ResponseEntity<List<ProductCategories>> getAllProductCategorys() {
        List<ProductCategories> ProductCategorys = ProductCategoryService.getAllProductCategorys();
        return new ResponseEntity<>(ProductCategorys, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/ProductCategory/1
    @PutMapping("{id}")
    public ResponseEntity<ProductCategories> updateProductCategory(@PathVariable("id")  UUID ProductCategoryId,
            @RequestBody ProductCategories ProductCategory) {
        ProductCategory.setId(ProductCategoryId);
        ProductCategories updateProductCategory = ProductCategoryService.updateProductCategory(ProductCategory);
        return new ResponseEntity<>(updateProductCategory, HttpStatus.OK);
    }

    // Delete ProductCategory REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductCategory(@PathVariable("id") UUID ProductCategoryId) {
        ProductCategoryService.deleteProductCategory(ProductCategoryId);
        return new ResponseEntity<>("ProductCategory successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}