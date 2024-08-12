package com.lamnhattan.example003.repository;

import java.util.List;
import java.util.UUID;

// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.lamnhattan.example003.entity.Product_tags;

public interface ProductTagRepository extends CrudRepository<Product_tags, UUID>{
    List<Product_tags> findByTagId(Long tagId);
}
