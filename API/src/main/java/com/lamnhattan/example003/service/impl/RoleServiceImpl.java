package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Product;
import com.lamnhattan.example003.entity.Role;
import com.lamnhattan.example003.repository.RoleRepository;
import com.lamnhattan.example003.service.RoleService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class RoleServiceImpl implements RoleService {
    private RoleRepository RoleRepository;

    @Override
    public Role createRole(Role Role) {
        return RoleRepository.save(Role);
    }

    @Override
    public Role getRoleById(Long RoleId) {
        Optional<Role> optionalRole = RoleRepository.findById(RoleId);
        return optionalRole.get();
    }

    @Override
    public List<Role> getAllRoles() {
        return RoleRepository.findAll();
    }

    @Override
    public Role updateRole(Role Role) {
        Role existingRole = RoleRepository.findById(Role.getId()).get();
        existingRole.setRoleName(Role.getRoleName());
        existingRole.setPrivileges(Role.getPrivileges());
        Role updatedRole = RoleRepository.save(existingRole);
        return updatedRole;
    }

    @Override
    public void deleteRole(Long RoleId) {
        RoleRepository.deleteById(RoleId);
    }

}
