package com.lamnhattan.example003.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Category;


public interface CategoryService {
    List<Category> getAllCategoriess();
    Category getCategoryById(UUID id);
    Category saveCategory(Category category);
    void deleteCategory(UUID id);
    boolean existsById(UUID categoryId);
    Category updateCategory(Category Category) ;
}
