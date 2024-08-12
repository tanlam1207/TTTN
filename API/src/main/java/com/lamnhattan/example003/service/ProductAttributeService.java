package com.lamnhattan.example003.service;

import java.util.List;

import com.lamnhattan.example003.entity.ProductAttribute;


public interface ProductAttributeService {
    ProductAttribute createProductAttribute(ProductAttribute ProductAttribute);

    ProductAttribute getProductAttributeById(ProductAttribute.ProductAttributeID ProductAttributeId);

    List<ProductAttribute> getAllProductAttributes();

    ProductAttribute updateProductAttribute(ProductAttribute ProductAttribute);

    void deleteProductAttribute(ProductAttribute.ProductAttributeID ProductAttributeId);
}
