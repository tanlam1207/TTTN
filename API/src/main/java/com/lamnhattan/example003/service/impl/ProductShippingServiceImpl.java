package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.ProductShipping;
import com.lamnhattan.example003.repository.ProductShippingRepository;
import com.lamnhattan.example003.service.ProductShippingService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ProductShippingServiceImpl implements ProductShippingService {
    private ProductShippingRepository ProductShippingRepository;

    @Override
    public ProductShipping createProductShipping(ProductShipping ProductShipping) {
        return ProductShippingRepository.save(ProductShipping);
    }

    @Override
    public ProductShipping getProductShippingById(ProductShipping.ProductShippingID ProductShippingId) {
        Optional<ProductShipping> optionalProductShipping = ProductShippingRepository.findById(ProductShippingId);
        return optionalProductShipping.get();
    }

    @Override
    public List<ProductShipping> getAllProductShippings() {
        return ProductShippingRepository.findAll();
    }

    @Override
    public ProductShipping updateProductShipping(ProductShipping ProductShipping) {
        ProductShipping existingProductShipping = ProductShippingRepository.findById(ProductShipping.getId()).get();
        existingProductShipping.setProduct(ProductShipping.getProduct());
        existingProductShipping.setShipping(ProductShipping.getShipping());
        existingProductShipping.setShip_charge(ProductShipping.getShip_charge());
        existingProductShipping.setFree(ProductShipping.getFree());
        existingProductShipping.setEstimated_days(ProductShipping.getEstimated_days());
        ProductShipping updatedProductShipping = ProductShippingRepository.save(existingProductShipping);
        return updatedProductShipping;
    }

    @Override
    public void deleteProductShipping(ProductShipping.ProductShippingID ProductShippingId) {
        ProductShippingRepository.deleteById(ProductShippingId);
    }

}
