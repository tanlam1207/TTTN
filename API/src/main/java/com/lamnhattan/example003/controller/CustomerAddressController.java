package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.CustomerAddress;
import com.lamnhattan.example003.service.CustomerAddressService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/customerAddresss")
public class CustomerAddressController {
    
    private CustomerAddressService CustomerAddressService;

    // Create CustomerAddress rest API
    @PostMapping
    public ResponseEntity<CustomerAddress> createCustomerAddress(@RequestBody CustomerAddress CustomerAddress) {
        CustomerAddress savedCustomerAddress = CustomerAddressService.createCustomerAddress(CustomerAddress);
        return new ResponseEntity<>(savedCustomerAddress, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/CustomerAddress/1
    @GetMapping("{id}")
    public ResponseEntity<CustomerAddress> getCustomerAddressById(@PathVariable("id") Long CustomerAddressId) {
        CustomerAddress CustomerAddress = CustomerAddressService.getCustomerAddressById(CustomerAddressId);
        return new ResponseEntity<>(CustomerAddress, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/CustomerAddress
    @GetMapping
    public ResponseEntity<List<CustomerAddress>> getAllCustomerAddresss() {
        List<CustomerAddress> CustomerAddresss = CustomerAddressService.getAllCustomerAddresss();
        return new ResponseEntity<>(CustomerAddresss, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/CustomerAddress/1
    @PutMapping("{id}")
    public ResponseEntity<CustomerAddress> updateCustomerAddress(@PathVariable("id") Long CustomerAddressId,
            @RequestBody CustomerAddress CustomerAddress) {
        CustomerAddress.setId(CustomerAddressId);
        CustomerAddress updateCustomerAddress = CustomerAddressService.updateCustomerAddress(CustomerAddress);
        return new ResponseEntity<>(updateCustomerAddress, HttpStatus.OK);
    }

    // Delete CustomerAddress REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomerAddress(@PathVariable("id") Long CustomerAddressId) {
        CustomerAddressService.deleteCustomerAddress(CustomerAddressId);
        return new ResponseEntity<>("CustomerAddress successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}