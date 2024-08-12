package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.CustomerAddress;
import com.lamnhattan.example003.repository.CustomerAddressRepository;
import com.lamnhattan.example003.service.CustomerAddressService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class CustomerAddressServiceImpl implements CustomerAddressService {
    private CustomerAddressRepository CustomerAddressRepository;

    @Override
    public CustomerAddress createCustomerAddress(CustomerAddress CustomerAddress) {
        return CustomerAddressRepository.save(CustomerAddress);
    }

    @Override
    public CustomerAddress getCustomerAddressById(Long CustomerAddressId) {
        Optional<CustomerAddress> optionalCustomerAddress = CustomerAddressRepository.findById(CustomerAddressId);
        return optionalCustomerAddress.get();
    }

    @Override
    public List<CustomerAddress> getAllCustomerAddresss() {
        return CustomerAddressRepository.findAll();
    }

    @Override
    public CustomerAddress updateCustomerAddress(CustomerAddress CustomerAddress) {
        CustomerAddress existingCustomerAddress = CustomerAddressRepository.findById(CustomerAddress.getId()).get();
        existingCustomerAddress.setCustomer(CustomerAddress.getCustomer());
        existingCustomerAddress.setAddress_line1(CustomerAddress.getAddress_line1());
        existingCustomerAddress.setAddress_line2(CustomerAddress.getAddress_line2());
        existingCustomerAddress.setPostal_code(CustomerAddress.getPostal_code());
        existingCustomerAddress.setCountry(CustomerAddress.getCountry());
        existingCustomerAddress.setCity(CustomerAddress.getCity());
        existingCustomerAddress.setPhone_number(CustomerAddress.getPhone_number());

        CustomerAddress updatedCustomerAddress = CustomerAddressRepository.save(existingCustomerAddress);
        return updatedCustomerAddress;
    }

    @Override
    public void deleteCustomerAddress(Long CustomerAddressId) {
        CustomerAddressRepository.deleteById(CustomerAddressId);
    }

}
