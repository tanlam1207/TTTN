package com.lamnhattan.example003.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Category;
import com.lamnhattan.example003.repository.CategoryRepository;
import com.lamnhattan.example003.service.CategoryService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategoriess() {
        return categoryRepository.findAll();
    }  

    @Override
    public Category getCategoryById(UUID id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.get();
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }
    @Override
     public Category updateCategory(Category Category)
     {
        Category existingCategory = categoryRepository.findById(Category.getCategoryId()).get();
        existingCategory.setName(Category.getName());
        existingCategory.setIcon(Category.getIcon());
        existingCategory.setDescription(Category.getDescription());
        existingCategory.setImage(Category.getImage());
        existingCategory.setParentId(Category.getParentId());

        // existingProduct.setPublished(Product.isPublished());
        existingCategory.setCreated_at(Category.getCreated_at());
        existingCategory.setUpdated_at(Category.getUpdated_at());
        existingCategory.setCreatedBy(Category.getCreatedBy());
        existingCategory.setUpdatedBy(Category.getUpdatedBy());

        Category updatedCategory = categoryRepository.save(existingCategory);
        return updatedCategory;
     }

    @Override
    public boolean existsById(UUID categoryId) {
        return categoryRepository.existsById(categoryId);
    }
}