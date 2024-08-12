package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.Tag;
import com.lamnhattan.example003.service.TagService;

import java.util.List;

@CrossOrigin(origins = "*")@RestController
@AllArgsConstructor
@RequestMapping("/tags")
public class TagController {
    
    private TagService TagService;

    // Create Tag rest API
    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody Tag Tag) {
        Tag savedTag = TagService.createTag(Tag);
        return new ResponseEntity<>(savedTag, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/Tag/1
    @GetMapping("{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable("id") Long TagId) {
        Tag Tag = TagService.getTagById(TagId);
        return new ResponseEntity<>(Tag, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/Tag
    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> Tags = TagService.getAllTags();
        return new ResponseEntity<>(Tags, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/Tag/1
    @PutMapping("{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable("id") Long TagId,
            @RequestBody Tag Tag) {
        Tag.setId(TagId);
        Tag updateTag = TagService.updateTag(Tag);
        return new ResponseEntity<>(updateTag, HttpStatus.OK);
    }

    // Delete Tag REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTag(@PathVariable("id") Long TagId) {
        TagService.deleteTag(TagId);
        return new ResponseEntity<>("Tag successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}