package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.Staff_roles;
import com.lamnhattan.example003.service.StaffRoleService;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/staffRoles")
public class StaffRoleController {
    
    private StaffRoleService StaffRoleService;

    // Create StaffRole rest API
    @PostMapping
    public ResponseEntity<Staff_roles> createStaffRole(@RequestBody Staff_roles StaffRole) {
        Staff_roles savedStaffRole = StaffRoleService.createStaffRole(StaffRole);
        return new ResponseEntity<>(savedStaffRole, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/StaffRole/1
    @GetMapping("{id}")
    public ResponseEntity<Staff_roles> getStaffRoleById(@PathVariable("id") UUID StaffRoleId) {
        Staff_roles StaffRole = StaffRoleService.getStaffRoleById(StaffRoleId);
        return new ResponseEntity<>(StaffRole, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/StaffRole
    @GetMapping
    public ResponseEntity<List<Staff_roles>> getAllStaffRoles() {
        List<Staff_roles> StaffRoles = StaffRoleService.getAllStaffRoles();
        return new ResponseEntity<>(StaffRoles, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/StaffRole/1
    // @PutMapping("{id}")
    // public ResponseEntity<Staff_roles> updateStaffRole(@PathVariable("id") UUID StaffRoleId,
    //         @RequestBody Staff_roles StaffRole) {
    //     StaffRole.setId(StaffRoleId);
    //     StaffRole updateStaffRole = StaffRoleService.updateStaffRole(StaffRole);
    //     return new ResponseEntity<>(updateStaffRole, HttpStatus.OK);
    // }

    // Delete StaffRole REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStaffRole(@PathVariable("id") UUID StaffRoleId) {
        StaffRoleService.deleteStaffRole(StaffRoleId);
        return new ResponseEntity<>("StaffRole successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}