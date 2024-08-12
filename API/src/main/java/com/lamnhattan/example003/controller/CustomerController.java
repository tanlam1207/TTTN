package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.Customer;
import com.lamnhattan.example003.service.CustomerService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/customers")
public class CustomerController {
    
    private CustomerService CustomerService;

    // Create Customer rest API
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer Customer) {
        Customer savedCustomer = CustomerService.createCustomer(Customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/Customer/1
    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long CustomerId) {
        Customer Customer = CustomerService.getCustomerById(CustomerId);
        return new ResponseEntity<>(Customer, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/Customer
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> Customers = CustomerService.getAllCustomers();
        return new ResponseEntity<>(Customers, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/Customer/1
    @PutMapping("{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long CustomerId,
            @RequestBody Customer Customer) {
        Customer.setId(CustomerId);
        Customer updateCustomer = CustomerService.updateCustomer(Customer);
        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
    }

    // Delete Customer REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long CustomerId) {
        CustomerService.deleteCustomer(CustomerId);
        return new ResponseEntity<>("Customer successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}