package com.lamnhattan.example003.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lamnhattan.example003.entity.Staff_roles;

public interface StaffRoleRepository extends JpaRepository<Staff_roles, UUID>{
    
}
