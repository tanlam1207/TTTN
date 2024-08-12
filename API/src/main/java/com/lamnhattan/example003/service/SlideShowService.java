package com.lamnhattan.example003.service;

import java.util.List;
import java.util.UUID;

import com.lamnhattan.example003.entity.Slider_shows;


public interface SlideShowService {
    Slider_shows createSlideShow(Slider_shows SlideShow);

    Slider_shows getSlideShowById(UUID SlideShowId);

    List<Slider_shows> getAllSlideShows();

    // Slider_shows updateSlideShow(Slider_shows SlideShow);

    void deleteSlideShow(UUID SlideShowId);
}
