package com.lamnhattan.example003.service;


import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lamnhattan.example003.Model.ProductTagModels;
import com.lamnhattan.example003.entity.Product;


public interface ProductService {
    Product createProduct(Product Product);

    Product getProductById(UUID ProductId);

    List<Product> getAllProducts();

    Product updateProduct(Product Product);

    void deleteProduct(UUID ProductId);
}
