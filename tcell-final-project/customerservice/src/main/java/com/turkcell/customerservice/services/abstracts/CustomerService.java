package com.turkcell.customerservice.services.abstracts;
import com.turkcell.customerservice.services.dtos.request.CustomerAddRequest;
import com.turkcell.customerservice.services.dtos.request.CustomerAddressAddRequest;
import com.turkcell.customerservice.services.dtos.request.CustomerContactAdd;
import com.turkcell.customerservice.services.dtos.request.CustomerUpdateRequest;
import com.turkcell.customerservice.services.dtos.response.CustomerAddressGet;
import com.turkcell.customerservice.services.dtos.response.CustomerContactGet;
import com.turkcell.customerservice.services.dtos.response.CustomerGetResponse;

import java.util.List;

public interface CustomerService {
    void addCustomer(CustomerAddRequest request);
    CustomerGetResponse getCustomerById(int id);

    List<CustomerGetResponse> getCustomers();
    void deleteCustomerById(int id);

    void updateCustomerById(CustomerUpdateRequest request);

    void addAddressToCustomer(CustomerAddressAddRequest request);

    List<CustomerAddressGet> getCustomerAdressesByCustomerId(int id);

    void deleteCustomerAddressByAddressId(int addressId);
    void addContactToCustomer(CustomerContactAdd request);
    List<CustomerContactGet> getCustomerContactsByCustomerId(int customerId);
    void deleteCustomerContactByContactId(int contactId);
}
