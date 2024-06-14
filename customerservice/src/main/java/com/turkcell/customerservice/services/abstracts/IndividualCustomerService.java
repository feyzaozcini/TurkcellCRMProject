package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.services.dtos.request.*;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerGetResponse;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerSearchResponse;

import java.util.List;

public interface IndividualCustomerService {
    void addCustomer(IndividualCustomerAddRequest request);

    IndividualCustomerGetResponse getCustomerById(int id);

    List<IndividualCustomerGetResponse> getCustomers();

    void deleteCustomerById(int id);

    void updateCustomer(IndividualCustomerUpdateRequest request);

    List<IndividualCustomerSearchResponse> searchCustomer(IndividualCustomerSearchRequest request, int page, int size);
}
