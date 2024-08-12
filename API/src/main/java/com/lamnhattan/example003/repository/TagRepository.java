package com.lamnhattan.example003.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lamnhattan.example003.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{
    
}
