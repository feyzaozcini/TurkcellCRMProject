package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.services.dtos.request.*;
import com.turkcell.customerservice.services.dtos.response.CreatedIndividualCustomerResponse;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerGetResponse;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerSearchResponse;
import com.turkcell.customerservice.services.dtos.response.UpdatedIndividualCustomerResponse;

import java.util.List;

public interface IndividualCustomerService {
    CreatedIndividualCustomerResponse addCustomer(IndividualCustomerAddRequest request);

    IndividualCustomerGetResponse getCustomerById(int id);

    List<IndividualCustomerGetResponse> getCustomers();

    void deleteCustomerById(int id);

    UpdatedIndividualCustomerResponse updateCustomer(IndividualCustomerUpdateRequest request);

    List<IndividualCustomerSearchResponse> searchCustomer(IndividualCustomerSearchRequest request, int page, int size);
}
