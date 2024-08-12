package com.lamnhattan.example003.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lamnhattan.example003.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
