
package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.Role;
import com.lamnhattan.example003.service.RoleService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/roles")
public class RoleController {
    
    private RoleService RoleService;

    // Create Role rest API
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role Role) {
        Role savedRole = RoleService.createRole(Role);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/Role/1
    @GetMapping("{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") Long RoleId) {
        Role Role = RoleService.getRoleById(RoleId);
        return new ResponseEntity<>(Role, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/Role
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> Roles = RoleService.getAllRoles();
        return new ResponseEntity<>(Roles, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/Role/1
    @PutMapping("{id}")
    public ResponseEntity<Role> updateRole(@PathVariable("id") Long RoleId,
            @RequestBody Role Role) {
        Role.setId(RoleId);
        Role updateRole = RoleService.updateRole(Role);
        return new ResponseEntity<>(updateRole, HttpStatus.OK);
    }

    // Delete Role REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long RoleId) {
        RoleService.deleteRole(RoleId);
        return new ResponseEntity<>("Role successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}