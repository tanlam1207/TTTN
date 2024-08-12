package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Product_tags;
import com.lamnhattan.example003.repository.ProductTagRepository;
import com.lamnhattan.example003.service.ProductTagService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor

public class ProductTagServiceImpl implements ProductTagService {
    private ProductTagRepository ProductTagRepository;

    @Override
    public Product_tags createProductTag(Product_tags ProductTag) {
        return ProductTagRepository.save(ProductTag);
    }

    @Override
    public Product_tags getProductTagById(UUID ProductTagId) {
        Optional<Product_tags> optionalProductTag = ProductTagRepository.findById(ProductTagId);
        return optionalProductTag.get();
    }

    @Override
    public List<Product_tags> getAllProductTags() {
        Iterable<Product_tags> productTagsIterable = ProductTagRepository.findAll();
        return StreamSupport.stream(productTagsIterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Product_tags updateProductTag(Product_tags ProductTag) {
        Product_tags existingProductTag = ProductTagRepository.findById(ProductTag.getId()).get();
        existingProductTag.setProduct(ProductTag.getProduct());
        existingProductTag.setTag(ProductTag.getTag());
        existingProductTag.setProductId(ProductTag.getProductId());
        existingProductTag.setTagid(ProductTag.getTagid());
      
        Product_tags updatedProductTag = ProductTagRepository.save(existingProductTag);
        return updatedProductTag;
    }

    @Override
    public void deleteProductTag(UUID ProductTagId) {
        ProductTagRepository.deleteById(ProductTagId);
    }

}
