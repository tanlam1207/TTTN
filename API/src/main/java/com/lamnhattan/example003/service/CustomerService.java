package com.lamnhattan.example003.service;

import java.util.List;

import com.lamnhattan.example003.entity.Customer;


public interface CustomerService {
    Customer createCustomer(Customer Customer);

    Customer getCustomerById(Long CustomerId);

    List<Customer> getAllCustomers();

    Customer updateCustomer(Customer Customer);

    void deleteCustomer(Long CustomerId);
}
