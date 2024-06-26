package com.turkcell.customerservice.services.mappers;

import com.turkcell.customerservice.entities.Address;
import com.turkcell.customerservice.services.dtos.request.AddressUpdateRequest;
import com.turkcell.customerservice.services.dtos.request.IndividualCustomerAddressAddRequest;
import com.turkcell.customerservice.services.dtos.response.CreatedAddressResponse;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerAddressGet;
import com.turkcell.customerservice.services.dtos.response.UpdatedAddressResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(target = "customer.id", source = "customerId")
    Address addressFromAddRequest(IndividualCustomerAddressAddRequest add);

    IndividualCustomerAddressGet getResponseFromAddress(Address address);
    @Mapping(target = "customerId", source = "customer.id")
    CreatedAddressResponse getResponseFromCreatedAddress(Address address);
    @Mapping(target = "customerId", source = "customer.id")
    UpdatedAddressResponse getResponseFromUpdatedAddress(Address address);

    @Mapping(target = "customer", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address addressFromUpdateRequest(AddressUpdateRequest request, @MappingTarget Address address);
}
