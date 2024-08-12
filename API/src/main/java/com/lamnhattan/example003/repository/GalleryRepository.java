package com.lamnhattan.example003.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lamnhattan.example003.entity.Gallery;

public interface GalleryRepository extends JpaRepository<Gallery, UUID>{
    List<Gallery> findByProductId(UUID productId);
}
