package com.lamnhattan.example003.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lamnhattan.example003.service.UploadImageService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@AllArgsConstructor
@RequestMapping("/Upload")

@CrossOrigin(origins = "*")
public class UploadImageController {

    private UploadImageService uploadImageService;
  @PostMapping("single")
    public String postMethodName(@RequestParam("image") MultipartFile file) {
        //TODO: process POST request
       String url = uploadImageService.uploadImage(file, UUID.randomUUID().toString());
    return url;
    }
    @PostMapping("multiple")
    public List<String> uploadMultipleImages(@RequestParam("images") List<MultipartFile> images) {
        return images.stream()
                .map(image -> uploadImageService.uploadImage(image, UUID.randomUUID().toString()))
                .collect(Collectors.toList());
    }
    
    
}
