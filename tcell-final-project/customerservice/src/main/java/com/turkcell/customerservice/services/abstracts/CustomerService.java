package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.services.dtos.request.CustomerAddRequest;
import com.turkcell.customerservice.services.dtos.request.CustomerAddressAdd;
import com.turkcell.customerservice.services.dtos.request.CustomerUpdateRequest;
import com.turkcell.customerservice.services.dtos.response.CustomerAddressesGetResponse;
import com.turkcell.customerservice.services.dtos.response.CustomerGetResponse;

import java.util.List;

public interface CustomerService {
    void addCustomer(CustomerAddRequest request);
    CustomerGetResponse getCustomerById(int id);

    List<CustomerGetResponse> getCustomers();
    void deleteCustomerById(int id);

    void updateCustomerById(int id, CustomerUpdateRequest request);

    void addCustomerAddress(int id, CustomerAddressAdd request);

    List<CustomerAddressesGetResponse> getCustomerAdressesById(int id);

    void deleteCustomerAddressByAddressId(int addressId);
}
