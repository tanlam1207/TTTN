package com.lamnhattan.example003.service;

import java.util.List;

import com.lamnhattan.example003.entity.ProductShipping;


public interface ProductShippingService {
    ProductShipping createProductShipping(ProductShipping ProductShipping);

    ProductShipping getProductShippingById(ProductShipping.ProductShippingID ProductShippingId);

    List<ProductShipping> getAllProductShippings();

    ProductShipping updateProductShipping(ProductShipping ProductShipping);

    void deleteProductShipping(ProductShipping.ProductShippingID ProductShippingId);
}
