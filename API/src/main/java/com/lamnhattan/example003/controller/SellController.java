package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.Sell;
import com.lamnhattan.example003.service.SellService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/sells")
public class SellController {
    
    private SellService SellService;

    // Create Sell rest API
    @PostMapping
    public ResponseEntity<Sell> createSell(@RequestBody Sell Sell) {
        Sell savedSell = SellService.createSell(Sell);
        return new ResponseEntity<>(savedSell, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/Sell/1
    @GetMapping("{id}")
    public ResponseEntity<Sell> getSellById(@PathVariable("id") Long SellId) {
        Sell Sell = SellService.getSellById(SellId);
        return new ResponseEntity<>(Sell, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/Sell
    @GetMapping
    public ResponseEntity<List<Sell>> getAllSells() {
        List<Sell> Sells = SellService.getAllSells();
        return new ResponseEntity<>(Sells, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/Sell/1
    @PutMapping("{id}")
    public ResponseEntity<Sell> updateSell(@PathVariable("id") Long SellId,
            @RequestBody Sell Sell) {
        Sell.setId(SellId);
        Sell updateSell = SellService.updateSell(Sell);
        return new ResponseEntity<>(updateSell, HttpStatus.OK);
    }

    // Delete Sell REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSell(@PathVariable("id") Long SellId) {
        SellService.deleteSell(SellId);
        return new ResponseEntity<>("Sell successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}