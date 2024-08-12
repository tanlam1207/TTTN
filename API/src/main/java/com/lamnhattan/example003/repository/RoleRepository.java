package com.lamnhattan.example003.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lamnhattan.example003.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    
}
