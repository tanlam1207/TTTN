package com.lamnhattan.example003.service;

import java.util.List;
import java.util.UUID;

import com.lamnhattan.example003.entity.ProductCategories;


public interface ProductCategoryService {
    ProductCategories createProductCategory(ProductCategories ProductCategory);

    ProductCategories getProductCategoryById(UUID ProductCategoryId);

    List<ProductCategories> getAllProductCategorys();

    ProductCategories updateProductCategory(ProductCategories ProductCategory);

    void deleteProductCategory(UUID ProductCategoryId);
}
