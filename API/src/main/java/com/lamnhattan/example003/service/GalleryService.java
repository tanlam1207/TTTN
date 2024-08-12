package com.lamnhattan.example003.service;

import java.util.List;
import java.util.UUID;

import com.lamnhattan.example003.entity.Gallery;


public interface GalleryService {
    Gallery createGallery(Gallery Gallery);

    Gallery getGalleryById(UUID GalleryId);

    List<Gallery> getAllGallerys();

    Gallery updateGallery(Gallery Gallery);

    void deleteGallery(UUID GalleryId);
    List<Gallery> getGalleriesByProductId(UUID productId);
}
