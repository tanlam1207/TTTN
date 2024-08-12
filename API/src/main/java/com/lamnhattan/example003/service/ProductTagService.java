package com.lamnhattan.example003.service;

import java.util.List;
import java.util.UUID;

import com.lamnhattan.example003.entity.Product_tags;


public interface ProductTagService {
    Product_tags createProductTag(Product_tags ProductTag);

    Product_tags getProductTagById(UUID ProductTagId);

    List<Product_tags> getAllProductTags();

    Product_tags updateProductTag(Product_tags ProductTag);

    void deleteProductTag(UUID ProductTagId);
}
