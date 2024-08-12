package com.lamnhattan.example003.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.Brand;
import com.lamnhattan.example003.service.BrandService;
import lombok.*;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("brands")

public class BrandController {
     
    private BrandService BrandService;

    // Create Brand rest API
    @PostMapping
    public ResponseEntity<Brand> createBrand(@RequestBody Brand Brand) {
        Brand savedBrand = BrandService.createBrand(Brand);
        return new ResponseEntity<>(savedBrand, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/Brand/1
    @GetMapping("{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable("id") Long BrandId) {
        Brand Brand = BrandService.getBrandById(BrandId);
        return new ResponseEntity<>(Brand, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/Brand
    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands() {
        List<Brand> Brands = BrandService.getAllBrands();
        return new ResponseEntity<>(Brands, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/Brand/1
    @PutMapping("{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable("id") Long BrandId,
            @RequestBody Brand Brand) {
        Brand.setId(BrandId);
        Brand updateBrand = BrandService.updateBrand(Brand);
        return new ResponseEntity<>(updateBrand, HttpStatus.OK);
    }

    // Delete Brand REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable("id") Long BrandId) {
        BrandService.deleteBrand(BrandId);
        return new ResponseEntity<>("Brand successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
}
