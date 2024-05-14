package com.turkcell.customerservice.services.mappers;

import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.services.dtos.request.CustomerAddRequest;
import com.turkcell.customerservice.services.dtos.request.CustomerUpdateRequest;
import com.turkcell.customerservice.services.dtos.response.CustomerGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    Customer getCustomerFromAddRequest(CustomerAddRequest request);

    CustomerGetResponse getResponseFromCustomer(Customer customer);

    Customer customerFromUpdateRequest(CustomerUpdateRequest request);
}
