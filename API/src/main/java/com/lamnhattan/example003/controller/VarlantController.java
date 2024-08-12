package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.Variant;
import com.lamnhattan.example003.service.VarlantService;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/varlants")
public class VarlantController {
    
    private VarlantService VarlantService;

    // Create Varlant rest API
    @PostMapping
    public ResponseEntity<Variant> createVarlant(@RequestBody Variant Varlant) {
        Variant savedVarlant = VarlantService.createVarlant(Varlant);
        return new ResponseEntity<>(savedVarlant, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/Varlant/1
    @GetMapping("{id}")
    public ResponseEntity<Variant> getVarlantById(@PathVariable("id") UUID VarlantId) {
        Variant Varlant = VarlantService.getVarlantById(VarlantId);
        return new ResponseEntity<>(Varlant, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/Varlant
    @GetMapping
    public ResponseEntity<List<Variant>> getAllVarlants() {
        List<Variant> Varlants = VarlantService.getAllVarlants();
        return new ResponseEntity<>(Varlants, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/Varlant/1
    // @PutMapping("{id}")
    // public ResponseEntity<Variant> updateVarlant(@PathVariable("id") Long VarlantId,
    //         @RequestBody Variant Varlant) {
    //     Varlant.setId(VarlantId);
    //     Variant updateVarlant = VarlantService.updateVarlant(Varlant);
    //     return new ResponseEntity<>(updateVarlant, HttpStatus.OK);
    // }

    // Delete Varlant REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVarlant(@PathVariable("id") UUID VarlantId) {
        VarlantService.deleteVarlant(VarlantId);
        return new ResponseEntity<>("Varlant successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}