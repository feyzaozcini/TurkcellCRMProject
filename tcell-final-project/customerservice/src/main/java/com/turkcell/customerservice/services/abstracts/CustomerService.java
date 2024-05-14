package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.entities.Customer;

import java.util.List;

public interface CustomerService {
    void addCustomer(Customer customer);
    Customer getCustomerById(int id);

    List<Customer> getCustomers();
    void deleteCustomerById(int id);

    void updateCustomerById(int id, Customer customer);
}
