package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.ProductCategories;
// import com.lamnhattan.example003.entity.Product_categories;
import com.lamnhattan.example003.repository.ProductCategoryRepository;
import com.lamnhattan.example003.service.ProductCategoryService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor

public class ProductCategoryServiceImpl implements ProductCategoryService {
    private ProductCategoryRepository ProductCategoryRepository;

    @Override
    public ProductCategories createProductCategory(ProductCategories ProductCategory) {
        return ProductCategoryRepository.save(ProductCategory);
    }

    @Override
    public ProductCategories getProductCategoryById(UUID ProductCategoryId) {
        Optional<ProductCategories> optionalProductCategory = ProductCategoryRepository.findById(ProductCategoryId);
        return optionalProductCategory.get();
    }

    @Override
    public List<ProductCategories> getAllProductCategorys() {
        Iterable<ProductCategories> productCategoryIterable = ProductCategoryRepository.findAll();
        return StreamSupport.stream(productCategoryIterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public ProductCategories updateProductCategory(ProductCategories ProductCategory) {
        ProductCategories existingProductCategory = ProductCategoryRepository.findById(ProductCategory.getId()).get();
        existingProductCategory.setCategory_id(ProductCategory.getCategory_id());
        existingProductCategory.setProductId(ProductCategory.getProductId());
        existingProductCategory.setCategory(ProductCategory.getCategory());
        existingProductCategory.setProduct(ProductCategory.getProduct());

        ProductCategories updatedProductCategory = ProductCategoryRepository.save(existingProductCategory);
        return updatedProductCategory;
    }

    @Override
    public void deleteProductCategory(UUID ProductCategoryId) {
        ProductCategoryRepository.deleteById(ProductCategoryId);
    }

}
