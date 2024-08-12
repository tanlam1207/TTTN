package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.Slider_shows;
import com.lamnhattan.example003.service.SlideShowService;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("slideShows")
public class SlideShowController {
    
    private SlideShowService SlideShowService;

    // Create SlideShow rest API
    @PostMapping
    public ResponseEntity<Slider_shows> createSlideShow(@RequestBody Slider_shows SlideShow) {
        Slider_shows savedSlideShow = SlideShowService.createSlideShow(SlideShow);
        return new ResponseEntity<>(savedSlideShow, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/SlideShow/1
    @GetMapping("{id}")
    public ResponseEntity<Slider_shows> getSlideShowById(@PathVariable("id") UUID SlideShowId) {
        Slider_shows SlideShow = SlideShowService.getSlideShowById(SlideShowId);
        return new ResponseEntity<>(SlideShow, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/SlideShow
    @GetMapping
    public ResponseEntity<List<Slider_shows>> getAllSlideShows() {
        List<Slider_shows> SlideShows = SlideShowService.getAllSlideShows();
        return new ResponseEntity<>(SlideShows, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/SlideShow/1
    // @PutMapping("{id}")
    // public ResponseEntity<Slider_shows> updateSlideShow(@PathVariable("id") Long SlideShowId,
    //         @RequestBody Slider_shows SlideShow) {
    //     SlideShow.setId(SlideShowId);
    //     Slider_shows updateSlideShow = SlideShowService.updateSlideShow(SlideShow);
    //     return new ResponseEntity<>(updateSlideShow, HttpStatus.OK);
    // }

    // Delete SlideShow REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSlideShow(@PathVariable("id") UUID SlideShowId) {
        SlideShowService.deleteSlideShow(SlideShowId);
        return new ResponseEntity<>("SlideShow successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}