package com.lamnhattan.example003.service;

import java.util.List;

import com.lamnhattan.example003.entity.CustomerAddress;


public interface CustomerAddressService {
    CustomerAddress createCustomerAddress(CustomerAddress CustomerAddress);

    CustomerAddress getCustomerAddressById(Long CustomerAddressId);

    List<CustomerAddress> getAllCustomerAddresss();

    CustomerAddress updateCustomerAddress(CustomerAddress CustomerAddress);

    void deleteCustomerAddress(Long CustomerAddressId);
}
