package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.Model.ProductTagModels;
import com.lamnhattan.example003.entity.Category;
import com.lamnhattan.example003.entity.Product;
import com.lamnhattan.example003.entity.ProductBrand;
import com.lamnhattan.example003.entity.ProductCategories;
import com.lamnhattan.example003.entity.Product_tags;
import com.lamnhattan.example003.entity.Tag;
import com.lamnhattan.example003.repository.ProductBrandRepository;
import com.lamnhattan.example003.repository.ProductCategoryRepository;
import com.lamnhattan.example003.repository.ProductRepository;
import com.lamnhattan.example003.repository.ProductTagRepository;
import com.lamnhattan.example003.repository.TagRepository;
import com.lamnhattan.example003.service.ProductService;
import com.lamnhattan.example003.service.TagService;

import java.util.Collections;
import java.util.Comparator;
// import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
// import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository ProductRepository;

    @Autowired
    private ProductTagRepository productTagRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ProductBrandRepository brandRepository;

    @Override
    public Product createProduct(Product Product) {
    
        Product newProduct = ProductRepository.save(Product);
    
        UUID productId = newProduct.getProduct_id();
    
        // for (Product_tags productTags : Product.getProducttags()) {
        //     productTags.setProductId(productId);
        //     productTagRepository.save(productTags);
        // }
    
        for (ProductCategories productCat : Product.getProductCategories()) {
            productCat.setProductId(productId);
            productCat.setProduct(newProduct);
            productCategoryRepository.save(productCat);
        }
        for (ProductBrand productBra : Product.getBrands()) {
            productBra.setProductId(productId);
            productBra.setProduct(newProduct);
            brandRepository.save(productBra);
        }
    
        return newProduct;
    }
    

    @Override
    public Product getProductById(UUID ProductId) {
        Optional<Product> optionalProduct = ProductRepository.findById(ProductId);
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = ProductRepository.findAll();
        Collections.sort(products, Comparator.comparing(Product::getProduct_name));
        return products;
    }
    

    @Override
    public Product updateProduct(Product Product) {
        Product existingProduct = ProductRepository.findById(Product.getProduct_id()).get();
        existingProduct.setProduct_name(Product.getProduct_name());
        existingProduct.setSku(Product.getSku());
        existingProduct.setRegular_price(Product.getRegular_price());
        existingProduct.setDiscount_price(Product.getDiscount_price());
        existingProduct.setQuantity(Product.getQuantity());
        existingProduct.setShort_description(Product.getShort_description());
        existingProduct.setProduct_description(Product.getProduct_description());
        existingProduct.setProduct_weight(Product.getProduct_weight());
        existingProduct.setProduct_note(Product.getProduct_note());
        existingProduct.setProducttags(Product.getProducttags());
        existingProduct.setBrands(Product.getBrands());
        existingProduct.setProductCategories(Product.getProductCategories());
        existingProduct.setGalleries(Product.getGalleries());
        existingProduct.setPublished(Product.isPublished());
        existingProduct.setCreated_at(Product.getCreated_at());
        existingProduct.setUpdated_at(Product.getUpdated_at());
        existingProduct.setCreated_by(Product.getCreated_by());
        existingProduct.setUpdated_by(Product.getUpdated_by());

        Product updatedProduct = ProductRepository.save(existingProduct);
        return updatedProduct;
    }

    @Override
    public void deleteProduct(UUID productId) {
        // Lấy ra đối tượng Product mà bạn muốn xóa
        Optional<Product> optionalProduct = ProductRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            
            // Lấy danh sách các Product_tags liên quan đến sản phẩm
            List<ProductCategories> productcate = product.getProductCategories();
            List<Product_tags> productTags = product.getProducttags();
            List<ProductBrand> probra = product.getBrands();
            
            // Xóa từng Product_tags
            for (ProductCategories procate : productcate) {
                productCategoryRepository.delete(procate);
            }
            for (Product_tags protag : productTags) {
                productTagRepository.delete(protag);
            }
            for (ProductBrand probras : probra) {
                brandRepository.delete(probras);
            }
            
            
            // Xóa sản phẩm
            ProductRepository.deleteById(productId);
        }
    }
    
   
}
