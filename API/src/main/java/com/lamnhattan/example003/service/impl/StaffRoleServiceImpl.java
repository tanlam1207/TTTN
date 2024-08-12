package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Staff_roles;
import com.lamnhattan.example003.repository.StaffRoleRepository;
import com.lamnhattan.example003.service.StaffRoleService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor

public class StaffRoleServiceImpl implements StaffRoleService {
    private StaffRoleRepository StaffRoleRepository;

    @Override
    public Staff_roles createStaffRole(Staff_roles StaffRole) {
        return StaffRoleRepository.save(StaffRole);
    }

    @Override
    public Staff_roles getStaffRoleById(UUID StaffRoleId) {
        Optional<Staff_roles> optionalStaffRole = StaffRoleRepository.findById(StaffRoleId);
        return optionalStaffRole.get();
    }

    @Override
    public List<Staff_roles> getAllStaffRoles() {
        return StaffRoleRepository.findAll();
    }

    // @Override
    // public Staff_roles updateStaffRole(Staff_roles StaffRole) {
    //     Staff_roles existingStaffRole = StaffRoleRepository.findById(StaffRole.getId()).get();
    //     existingStaffRole.setStaffAccount(StaffRole.getStaffAccount());
    //     existingStaffRole.setRole(StaffRole.getRole());
    //     Staff_roles updatedStaffRole = StaffRoleRepository.save(existingStaffRole);
    //     return updatedStaffRole;
    // }

    @Override
    public void deleteStaffRole(UUID StaffRoleId) {
        StaffRoleRepository.deleteById(StaffRoleId);
    }

}
