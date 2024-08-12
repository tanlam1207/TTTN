package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Coupon;
import com.lamnhattan.example003.entity.Gallery;
import com.lamnhattan.example003.repository.GalleryRepository;
import com.lamnhattan.example003.service.GalleryService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor

public class GalleryServiceImpl implements GalleryService {
    private GalleryRepository GalleryRepository;

    @Override
    public Gallery createGallery(Gallery Gallery) {
        return GalleryRepository.save(Gallery);
    }

    @Override
    public Gallery getGalleryById(UUID GalleryId) {
        Optional<Gallery> optionalGallery = GalleryRepository.findById(GalleryId);
        return optionalGallery.get();
    }

    @Override
    public List<Gallery> getAllGallerys() {
        return GalleryRepository.findAll();
    }

    @Override
    public Gallery updateGallery(Gallery Gallery) {
        Gallery existingGallery = GalleryRepository.findById(Gallery.getId()).get();
        existingGallery.setProduct(Gallery.getProduct());
        existingGallery.setImage_path(Gallery.getImage_path());
        existingGallery.setThumbnail(Gallery.getThumbnail());
        existingGallery.setDisplay_order(Gallery.getDisplay_order());
        existingGallery.setCreated_at(Gallery.getCreated_at());
        existingGallery.setUpdated_at(Gallery.getUpdated_at());
        existingGallery.setCreatedBy(Gallery.getCreatedBy());
        existingGallery.setUpdatedBy(Gallery.getUpdatedBy());
        Gallery updatedGallery = GalleryRepository.save(existingGallery);
        return updatedGallery;
    }

    @Override
    public void deleteGallery(UUID GalleryId) {
        GalleryRepository.deleteById(GalleryId);
    }
    public List<Gallery> getGalleriesByProductId(UUID productId) {
        return GalleryRepository.findByProductId(productId);
    }
}
