package com.turkcell.customerservice.services.mappers;

import com.turkcell.customerservice.entities.Address;
import com.turkcell.customerservice.services.dtos.request.AddressUpdateRequest;
import com.turkcell.customerservice.services.dtos.request.CustomerAddressAddRequest;
import com.turkcell.customerservice.services.dtos.response.CustomerAddressGet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(target = "customer.id", source = "customerId")
    Address addressFromAddRequest(CustomerAddressAddRequest add);

    CustomerAddressGet getResponseFromAddress(Address address);

    @Mapping(target = "customer", ignore = true)
    Address addressFromUpdateRequest(AddressUpdateRequest request);
}
