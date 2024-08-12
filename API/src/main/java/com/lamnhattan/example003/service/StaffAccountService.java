package com.lamnhattan.example003.service;

import java.util.List;
import java.util.UUID;

import com.lamnhattan.example003.entity.StaffAccount;


public interface StaffAccountService {
    StaffAccount createStaffAccount(StaffAccount StaffAccount);

    StaffAccount getStaffAccountById(UUID StaffAccountId);

    List<StaffAccount> getAllStaffAccounts();

    // StaffAccount updateStaffAccount(StaffAccount StaffAccount);

    void deleteStaffAccount(UUID StaffAccountId);
}
