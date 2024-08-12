package com.lamnhattan.example003.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.lamnhattan.example003.service.ProductBrandService;
import com.lamnhattan.example003.entity.ProductBrand;
import com.lamnhattan.example003.repository.ProductBrandRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductBrandServiceImpl implements ProductBrandService{
    private ProductBrandRepository ProductBrandRepository;

    @Override
    public ProductBrand createProductBrand(ProductBrand ProductBrand) {
        return ProductBrandRepository.save(ProductBrand);
    }

    @Override
    public ProductBrand getProductBrandById(UUID ProductBrandId) {
        Optional<ProductBrand> optionalProductBrand = ProductBrandRepository.findById(ProductBrandId);
        return optionalProductBrand.get();
    }

    @Override
    public List<ProductBrand> getAllProductBrands() {
        Iterable<ProductBrand> ProductBrandsIterable = ProductBrandRepository.findAll();
        return StreamSupport.stream(ProductBrandsIterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    // @Override
    // public ProductBrand updateProductBrand(ProductBrand ProductBrand) {
    //     ProductBrand existingProductBrand = ProductBrandRepository.findById(ProductBrand.getTag()).get();
    //     existingProductBrand.setProduct(ProductBrand.getProduct());
      
    //     ProductBrand updatedProductBrand = ProductBrandRepository.save(existingProductBrand);
    //     return updatedProductBrand;
    // }

    @Override
    public void deleteProductBrand(UUID ProductBrandId) {
        ProductBrandRepository.deleteById(ProductBrandId);
    }
}
