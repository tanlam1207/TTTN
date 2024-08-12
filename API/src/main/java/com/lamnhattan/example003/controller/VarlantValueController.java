package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.Variant_value;
import com.lamnhattan.example003.service.VarlantValueService;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/varlantValues")
public class VarlantValueController {
    
    private VarlantValueService VarlantValueService;

    // Create VarlantValue rest API
    @PostMapping
    public ResponseEntity<Variant_value> createVarlantValue(@RequestBody Variant_value VarlantValue) {
        Variant_value savedVarlantValue = VarlantValueService.createVarlantValue(VarlantValue);
        return new ResponseEntity<>(savedVarlantValue, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/VarlantValue/1
    @GetMapping("{id}")
    public ResponseEntity<Variant_value> getVarlantValueById(@PathVariable("id") Long VarlantValueId) {
        Variant_value VarlantValue = VarlantValueService.getVarlantValueById(VarlantValueId);
        return new ResponseEntity<>(VarlantValue, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/VarlantValue
    @GetMapping
    public ResponseEntity<List<Variant_value>> getAllVarlantValues() {
        List<Variant_value> VarlantValues = VarlantValueService.getAllVarlantValues();
        return new ResponseEntity<>(VarlantValues, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/VarlantValue/1
    // @PutMapping("{id}")
    // public ResponseEntity<Variant_value> updateVarlantValue(@PathVariable("id") UUID VarlantValueId,
    //         @RequestBody Variant_value VarlantValue) {
    //     VarlantValue.setId(VarlantValueId);
    //     Variant_value updateVarlantValue = VarlantValueService.updateVarlantValue(VarlantValue);
    //     return new ResponseEntity<>(updateVarlantValue, HttpStatus.OK);
    // }

    // Delete VarlantValue REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVarlantValue(@PathVariable("id") Long VarlantValueId) {
        VarlantValueService.deleteVarlantValue(VarlantValueId);
        return new ResponseEntity<>("VarlantValue successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}