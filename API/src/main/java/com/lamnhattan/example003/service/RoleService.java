package com.lamnhattan.example003.service;

import java.util.List;

import com.lamnhattan.example003.entity.Role;


public interface RoleService {
    Role createRole(Role Role);

    Role getRoleById(Long RoleId);

    List<Role> getAllRoles();

    Role updateRole(Role Role);

    void deleteRole(Long RoleId);
}
