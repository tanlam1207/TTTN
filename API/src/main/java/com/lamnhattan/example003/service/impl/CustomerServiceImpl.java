package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Customer;
import com.lamnhattan.example003.repository.CustomerRepository;
import com.lamnhattan.example003.service.CustomerService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository CustomerRepository;

    @Override
    public Customer createCustomer(Customer Customer) {
        return CustomerRepository.save(Customer);
    }

    @Override
    public Customer getCustomerById(Long CustomerId) {
        Optional<Customer> optionalCustomer = CustomerRepository.findById(CustomerId);
        return optionalCustomer.get();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return CustomerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Customer Customer) {
        Customer existingCustomer = CustomerRepository.findById(Customer.getId()).get();
        existingCustomer.setFirst_name(Customer.getFirst_name());
        existingCustomer.setLast_name(Customer.getLast_name());
        existingCustomer.setPhone_number(Customer.getPhone_number());
        existingCustomer.setEmail(Customer.getEmail());
        existingCustomer.setPassword_hash(Customer.getPassword_hash());
        existingCustomer.setActive(Customer.getActive());
        existingCustomer.setRegistered_at(Customer.getRegistered_at());
        existingCustomer.setCreated_at(Customer.getCreated_at());
        Customer updatedCustomer = CustomerRepository.save(existingCustomer);
        return updatedCustomer;
    }

    @Override
    public void deleteCustomer(Long CustomerId) {
        CustomerRepository.deleteById(CustomerId);
    }

}
