package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.Card;
import com.lamnhattan.example003.service.CardService;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/cards")
public class CardController {
    
    private CardService CardService;

    // Create Card rest API
    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card Card) {
        Card savedCard = CardService.createCard(Card);
        return new ResponseEntity<>(savedCard, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/Card/1
    @GetMapping("{id}")
    public ResponseEntity<Card> getCardById(@PathVariable("id") Long CardId) {
        Card Card = CardService.getCardById(CardId);
        return new ResponseEntity<>(Card, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/Card
    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> Cards = CardService.getAllCards();
        return new ResponseEntity<>(Cards, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/Card/1
    @PutMapping("{id}")
    public ResponseEntity<Card> updateCard(@PathVariable("id") Long CardId,
            @RequestBody Card Card) {
        Card.setCard_id(CardId);
        Card updateCard = CardService.updateCard(Card);
        return new ResponseEntity<>(updateCard, HttpStatus.OK);
    }

    // Delete Card REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCard(@PathVariable("id") Long CardId) {
        CardService.deleteCard(CardId);
        return new ResponseEntity<>("Card successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
    @GetMapping("/search")
    public ResponseEntity<Card> getCardByGoogleCustomer(@RequestParam("google_customer") BigInteger google_customer) {
        Optional<Card> card = CardService.findByGooglecustomer(google_customer);
        if (card.isPresent()) {
            return new ResponseEntity<>(card.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }
    
}