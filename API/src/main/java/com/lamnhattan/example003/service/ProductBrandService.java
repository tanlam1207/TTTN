package com.lamnhattan.example003.service;

import java.util.List;
import java.util.UUID;

import com.lamnhattan.example003.entity.ProductBrand;

public interface ProductBrandService {


    ProductBrand createProductBrand(ProductBrand ProductBrand);

    ProductBrand getProductBrandById(UUID ProductBrandId);

    List<ProductBrand> getAllProductBrands();

    // ProductBrand updateProductBrand(ProductBrand ProductBrand);

    void deleteProductBrand(UUID ProductBrandId);
}
