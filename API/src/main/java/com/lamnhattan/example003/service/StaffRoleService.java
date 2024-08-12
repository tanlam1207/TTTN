package com.lamnhattan.example003.service;

import java.util.List;
import java.util.UUID;

import com.lamnhattan.example003.entity.Staff_roles;


public interface StaffRoleService {
    Staff_roles createStaffRole(Staff_roles StaffRole);

    Staff_roles getStaffRoleById(UUID StaffRoleId);

    List<Staff_roles> getAllStaffRoles();

    // Staff_roles updateStaffRole(Staff_roles StaffRole);

    void deleteStaffRole(UUID StaffRoleId);
}
