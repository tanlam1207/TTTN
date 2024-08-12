package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.Variant_attribute_value;
import com.lamnhattan.example003.service.VarlantAttributeValueService;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/varlantAttributeValues")
public class VarlantAttributeValueController {
    
    private VarlantAttributeValueService VarlantAttributeValueService;

    // Create VarlantAttributeValue rest API
    @PostMapping
    public ResponseEntity<Variant_attribute_value> createVarlantAttributeValue(@RequestBody Variant_attribute_value VarlantAttributeValue) {
        Variant_attribute_value savedVarlantAttributeValue = VarlantAttributeValueService.createVarlantAttributeValue(VarlantAttributeValue);
        return new ResponseEntity<>(savedVarlantAttributeValue, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/VarlantAttributeValue/1
    @GetMapping("{id}")
    public ResponseEntity<Variant_attribute_value> getVarlantAttributeValueById(@PathVariable("id") Long VarlantAttributeValueId) {
        Variant_attribute_value VarlantAttributeValue = VarlantAttributeValueService.getVarlantAttributeValueById(VarlantAttributeValueId);
        return new ResponseEntity<>(VarlantAttributeValue, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/VarlantAttributeValue
    @GetMapping
    public ResponseEntity<List<Variant_attribute_value>> getAllVarlantAttributeValues() {
        List<Variant_attribute_value> VarlantAttributeValues = VarlantAttributeValueService.getAllVarlantAttributeValues();
        return new ResponseEntity<>(VarlantAttributeValues, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/VarlantAttributeValue/1
    // @PutMapping("{id}")
    // public ResponseEntity<Variant_attribute_value> updateVarlantAttributeValue(@PathVariable("id") UUID VarlantAttributeValueId,
    //         @RequestBody Variant_attribute_value VarlantAttributeValue) {
    //     VarlantAttributeValue.setVariant_attribute_value_id(VarlantAttributeValueId);
    //     Variant_attribute_value updateVarlantAttributeValue = VarlantAttributeValueService.updateVarlantAttributeValue(VarlantAttributeValue);
    //     return new ResponseEntity<>(updateVarlantAttributeValue, HttpStatus.OK);
    // }

    // Delete VarlantAttributeValue REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVarlantAttributeValue(@PathVariable("id") Long VarlantAttributeValueId) {
        VarlantAttributeValueService.deleteVarlantAttributeValue(VarlantAttributeValueId);
        return new ResponseEntity<>("VarlantAttributeValue successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}