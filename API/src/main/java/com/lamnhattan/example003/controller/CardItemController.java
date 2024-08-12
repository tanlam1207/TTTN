package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.Card_item;
import com.lamnhattan.example003.service.CardItemService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/CardItems")
public class CardItemController {
    
    private CardItemService CardItemService;

    // Create CardItem rest API
    @PostMapping
    public ResponseEntity<Card_item> createCardItem(@RequestBody Card_item CardItem) {
        Card_item savedCardItem = CardItemService.createCardItem(CardItem);
        return new ResponseEntity<>(savedCardItem, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/CardItem/1
    @GetMapping("{id}")
    public ResponseEntity<Card_item> getCardItemById(@PathVariable("id") Long CardItemId) {
        Card_item CardItem = CardItemService.getCardItemById(CardItemId);
        return new ResponseEntity<>(CardItem, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/CardItem
    @GetMapping
    public ResponseEntity<List<Card_item>> getAllCardItems() {
        List<Card_item> CardItems = CardItemService.getAllCardItems();
        return new ResponseEntity<>(CardItems, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/CardItem/1
    @PutMapping("{id}")
    public ResponseEntity<Card_item> updateCardItem(@PathVariable("id") Long CardItemId,
            @RequestBody Card_item CardItem) {
        CardItem.setId(CardItemId);
        Card_item updateCardItem = CardItemService.updateCardItem(CardItem);
        return new ResponseEntity<>(updateCardItem, HttpStatus.OK);
    }

    // Delete CardItem REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCardItem(@PathVariable("id") Long CardItemId) {
        CardItemService.deleteCardItem(CardItemId);
        return new ResponseEntity<>("CardItem successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
    @GetMapping("/byCard/{cardId}")
    public ResponseEntity<List<Card_item>> getCardItemsByCardId(@PathVariable("cardId") Long cardId) {
        List<Card_item> cardItems = CardItemService.findByCardId(cardId);
        return new ResponseEntity<>(cardItems, HttpStatus.OK);
    }
    @DeleteMapping("/byCard/{cardId}")
    public ResponseEntity<String> deleteCardItemsByCardId(@PathVariable("cardId") Long cardId) {
        CardItemService.deleteCardItemsByCardId(cardId);
        return new ResponseEntity<>("All CardItems successfully deleted!", HttpStatus.OK);
    }
}