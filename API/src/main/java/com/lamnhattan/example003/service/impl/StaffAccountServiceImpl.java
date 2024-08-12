package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.StaffAccount;
import com.lamnhattan.example003.repository.StaffAccountRepository;
import com.lamnhattan.example003.service.StaffAccountService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor

public class StaffAccountServiceImpl implements StaffAccountService {
    private StaffAccountRepository StaffAccountRepository;

    @Override
    public StaffAccount createStaffAccount(StaffAccount StaffAccount) {
        return StaffAccountRepository.save(StaffAccount);
    }

    @Override
    public StaffAccount getStaffAccountById(UUID StaffAccountId) {
        Optional<StaffAccount> optionalStaffAccount = StaffAccountRepository.findById(StaffAccountId);
        return optionalStaffAccount.get();
    }

    @Override
    public List<StaffAccount> getAllStaffAccounts() {
        return StaffAccountRepository.findAll();
    }

    // @Override
    // public StaffAccount updateStaffAccount(StaffAccount StaffAccount) {
    //     StaffAccount existingStaffAccount = StaffAccountRepository.findById(StaffAccount.getId()).get();
    //     existingStaffAccount.setFirst_name(StaffAccount.getFirst_name());
    //     existingStaffAccount.setLast_name(StaffAccount.getLast_name());
    //     existingStaffAccount.setPhone_number(StaffAccount.getPhone_number());
    //     existingStaffAccount.setEmail(StaffAccount.getEmail());
    //     existingStaffAccount.setPassword_hash(StaffAccount.getPassword_hash());
    //     existingStaffAccount.setActive(StaffAccount.getActive());
    //     existingStaffAccount.setProfile_img(StaffAccount.getProfile_img());
    //     existingStaffAccount.setRegistered_at(StaffAccount.getRegistered_at());
    //     existingStaffAccount.setUpdated_at(StaffAccount.getUpdated_at());
    //     existingStaffAccount.setCreated_by(StaffAccount.getCreated_by());
    //     existingStaffAccount.setUpdated_by(StaffAccount.getUpdated_by());
    //     StaffAccount updatedStaffAccount = StaffAccountRepository.save(existingStaffAccount);
    //     return updatedStaffAccount;
    // }

    @Override
    public void deleteStaffAccount(UUID StaffAccountId) {
        StaffAccountRepository.deleteById(StaffAccountId);
    }

}
