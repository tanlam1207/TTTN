package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.StaffAccount;
import com.lamnhattan.example003.service.StaffAccountService;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("staffAccounts")
public class StaffAccountController {
    
    private StaffAccountService StaffAccountService;

    // Create StaffAccount rest API
    @PostMapping
    public ResponseEntity<StaffAccount> createStaffAccount(@RequestBody StaffAccount StaffAccount) {
        StaffAccount savedStaffAccount = StaffAccountService.createStaffAccount(StaffAccount);
        return new ResponseEntity<>(savedStaffAccount, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/StaffAccount/1
    @GetMapping("{id}")
    public ResponseEntity<StaffAccount> getStaffAccountById(@PathVariable("id") UUID StaffAccountId) {
        StaffAccount StaffAccount = StaffAccountService.getStaffAccountById(StaffAccountId);
        return new ResponseEntity<>(StaffAccount, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/StaffAccount
    @GetMapping
    public ResponseEntity<List<StaffAccount>> getAllStaffAccounts() {
        List<StaffAccount> StaffAccounts = StaffAccountService.getAllStaffAccounts();
        return new ResponseEntity<>(StaffAccounts, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/StaffAccount/1
    // @PutMapping("{id}")
    // public ResponseEntity<StaffAccount> updateStaffAccount(@PathVariable("id") UUID StaffAccountId,
    //         @RequestBody StaffAccount StaffAccount) {
    //     StaffAccount.setId(StaffAccountId);
    //     StaffAccount updateStaffAccount = StaffAccountService.updateStaffAccount(StaffAccount);
    //     return new ResponseEntity<>(updateStaffAccount, HttpStatus.OK);
    // }

    // Delete StaffAccount REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStaffAccount(@PathVariable("id") UUID StaffAccountId) {
        StaffAccountService.deleteStaffAccount(StaffAccountId);
        return new ResponseEntity<>("StaffAccount successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}