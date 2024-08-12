package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.lamnhattan.example003.entity.Gallery;
import com.lamnhattan.example003.entity.Product;
import com.lamnhattan.example003.service.GalleryService;
import com.lamnhattan.example003.service.ProductService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/galleries")
public class GalleryController {
    private static final String IMAGE_DIR = "static/images/";
    private static final String UPLOAD_DIR = "static/images/";
    private GalleryService GalleryService;
    private ProductService productService;

    // Create Gallery rest API
    @PostMapping
    public ResponseEntity<Gallery> createGallery(@RequestBody Gallery Gallery) {
        Gallery savedGallery = GalleryService.createGallery(Gallery);
        return new ResponseEntity<>(savedGallery, HttpStatus.CREATED);
    }
 @PostMapping("/upload")
    public String uploadImages(@RequestParam("images") List<MultipartFile> files) {
        try {
            // Kiểm tra sự tồn tại của sản phẩm
            // Product product = productService.getProductById(productId);
            // if (product == null) {
            //     // return ResponseEntity.status(HttpStatus.NOT_FOUND)
            //     //         .body("Product with ID " + productId + " not found.");
            //     return"lỗi";
            // }

            // List<ProductImage> uploadedImages = new ArrayList<>();

            for (MultipartFile file : files) {
                String originalFilename = file.getOriginalFilename();
                String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                // if (!extension.matches("\\.(jpg|jpeg|png|gif|webp)$")) {
                //     return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                //             .body("Invalid file format. Supported formats: jpg, jpeg, png, gif");
                // }

                String newFilename = UUID.randomUUID().toString() + extension;
                Path path = Paths.get(UPLOAD_DIR + newFilename);
                Files.write(path, file.getBytes());

                // ProductImage productImage = new ProductImage();
                // productImage.setImageUrl(newFilename);
                // productImage.setProduct(product);
                // uploadedImages.add(productImage);
            }

            // List<ProductImage> savedImages = productImageService.uploadAll(uploadedImages);

            // return ResponseEntity.status(HttpStatus.CREATED).body(savedImages);
            return"success";
        } catch (IOException e) {
            e.printStackTrace();
            // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading the files.");
            return"er";

        }
    }

    // Get product by id REST API
    // http://localhost:8080/api/Gallery/1
    @GetMapping("{id}")
    public ResponseEntity<Gallery> getGalleryById(@PathVariable("id") UUID GalleryId) {
        Gallery Gallery = GalleryService.getGalleryById(GalleryId);
        return new ResponseEntity<>(Gallery, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/Gallery
    @GetMapping
    public ResponseEntity<List<Gallery>> getAllGallerys() {
        List<Gallery> Gallerys = GalleryService.getAllGallerys();
        return new ResponseEntity<>(Gallerys, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/Gallery/1
    @PutMapping("{id}")
    public ResponseEntity<Gallery> updateGallery(@PathVariable("id") UUID GalleryId,
            @RequestBody Gallery Gallery) {
        Gallery.setId(GalleryId);
        Gallery updateGallery = GalleryService.updateGallery(Gallery);
        return new ResponseEntity<>(updateGallery, HttpStatus.OK);
    }

    // Delete Gallery REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteGallery(@PathVariable("id") UUID GalleryId) {
        GalleryService.deleteGallery(GalleryId);
        return new ResponseEntity<>("Gallery successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   @GetMapping("/by-product/{productId}")
public ResponseEntity<List<Gallery>> getGalleriesByProductId(@PathVariable("productId") UUID productId) {
    List<Gallery> galleries = GalleryService.getGalleriesByProductId(productId);
    return new ResponseEntity<>(galleries, HttpStatus.OK);
}
 @GetMapping("/img/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        try {
            File file = new File(IMAGE_DIR + imageName);
            if (file.exists()) {
                Resource resource = new FileSystemResource(file);
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) 
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}