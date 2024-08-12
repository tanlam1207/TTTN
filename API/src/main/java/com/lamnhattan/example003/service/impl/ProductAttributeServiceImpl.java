package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.ProductAttribute;
import com.lamnhattan.example003.repository.ProductAttributeRepository;
import com.lamnhattan.example003.service.ProductAttributeService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ProductAttributeServiceImpl implements ProductAttributeService {
    private ProductAttributeRepository ProductAttributeRepository;

    @Override
    public ProductAttribute createProductAttribute(ProductAttribute ProductAttribute) {
        return ProductAttributeRepository.save(ProductAttribute);
    }

    @Override
    public ProductAttribute getProductAttributeById(ProductAttribute.ProductAttributeID ProductAttributeId) {
        Optional<ProductAttribute> optionalProductAttribute = ProductAttributeRepository.findById(ProductAttributeId);
        return optionalProductAttribute.get();
    }

    @Override
    public List<ProductAttribute> getAllProductAttributes() {
        return ProductAttributeRepository.findAll();
    }

    @Override
    public ProductAttribute updateProductAttribute(ProductAttribute ProductAttribute) {
        ProductAttribute existingProductAttribute = ProductAttributeRepository.findById(ProductAttribute.getId()).get();
        existingProductAttribute.setProduct(ProductAttribute.getProduct());
        existingProductAttribute.setAttribute(ProductAttribute.getAttribute());
        ProductAttribute updatedProductAttribute = ProductAttributeRepository.save(existingProductAttribute);
        return updatedProductAttribute;
    }
    @Override
    public void deleteProductAttribute(ProductAttribute.ProductAttributeID ProductAttributeId) {
        ProductAttributeRepository.deleteById(ProductAttributeId);
    }

}
