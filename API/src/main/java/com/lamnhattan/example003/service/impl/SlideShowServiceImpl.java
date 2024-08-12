package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Slider_shows;
import com.lamnhattan.example003.repository.SlideShowRepository;
import com.lamnhattan.example003.service.SlideShowService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor

public class SlideShowServiceImpl implements SlideShowService {
    private SlideShowRepository SlideShowRepository;

    @Override
    public Slider_shows createSlideShow(Slider_shows SlideShow) {
        return SlideShowRepository.save(SlideShow);
    }

    @Override
    public Slider_shows getSlideShowById(UUID SlideShowId) {
        Optional<Slider_shows> optionalSlideShow = SlideShowRepository.findById(SlideShowId);
        return optionalSlideShow.get();
    }

    @Override
    public List<Slider_shows> getAllSlideShows() {
        return SlideShowRepository.findAll();
    }

    // @Override
    // public Slider_shows updateSlideShow(Slider_shows SlideShow) {
    //     Slider_shows existingSlideShow = SlideShowRepository.findById(SlideShow.getId()).get();
    //     existingSlideShow.setDestination_url(SlideShow.getDestination_url());
    //     existingSlideShow.setImage_path(SlideShow.getImage_path());
    //     existingSlideShow.setClicks(SlideShow.getClicks());
    //     existingSlideShow.setCreated_at(SlideShow.getCreated_at());
    //     existingSlideShow.setUpdated_at(SlideShow.getUpdated_at());
    //     existingSlideShow.setCreated_by(SlideShow.getCreated_by());
    //     existingSlideShow.setUpdated_by(SlideShow.getUpdated_by());
    //     SlideShow updatedSlideShow = SlideShowRepository.save(existingSlideShow);
    //     return updatedSlideShow;
    // }

    @Override
    public void deleteSlideShow(UUID SlideShowId) {
        SlideShowRepository.deleteById(SlideShowId);
    }

}
