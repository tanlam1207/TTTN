package com.lamnhattan.example003.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.ProductBrand;
import com.lamnhattan.example003.service.ProductBrandService;
import lombok.*;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("productBrands")
public class ProductBrandController {
     
    private ProductBrandService ProductBrandService;

    // Create ProductBrand rest API
    @PostMapping
    public ResponseEntity<ProductBrand> createProductBrand(@RequestBody ProductBrand ProductBrand) {
        ProductBrand savedProductBrand = ProductBrandService.createProductBrand(ProductBrand);
        return new ResponseEntity<>(savedProductBrand, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/ProductBrand/1
    @GetMapping("{id}")
    public ResponseEntity<ProductBrand> getProductBrandById(@PathVariable("id") UUID ProductBrandId) {
        ProductBrand ProductBrand = ProductBrandService.getProductBrandById(ProductBrandId);
        return new ResponseEntity<>(ProductBrand, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/ProductBrand
    @GetMapping
    public ResponseEntity<List<ProductBrand>> getAllProductBrands() {
        List<ProductBrand> ProductBrands = ProductBrandService.getAllProductBrands();
        return new ResponseEntity<>(ProductBrands, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/ProductBrand/1
    // @PutMapping("{id}")
    // public ResponseEntity<ProductBrand> updateProductBrand(@PathVariable("id") UUID ProductBrandId,
    //         @RequestBody ProductBrand ProductBrand) {
    //     ProductBrand.setId(ProductBrandId);
    //     ProductBrand updateProductBrand = ProductBrandService.updateProductBrand(ProductBrand);
    //     return new ResponseEntity<>(updateProductBrand, HttpStatus.OK);
    // }

    // Delete ProductBrand REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductBrand(@PathVariable("id") UUID ProductBrandId) {
        ProductBrandService.deleteProductBrand(ProductBrandId);
        return new ResponseEntity<>("ProductBrand successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
}
