package com.turkcell.customerservice.services.mappers;

import com.turkcell.customerservice.entities.Address;
import com.turkcell.customerservice.services.dtos.request.CustomerAddressAdd;
import com.turkcell.customerservice.services.dtos.response.CustomerAddressesGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address addressFromAddRequest(CustomerAddressAdd add);

    CustomerAddressesGetResponse getResponseFromAddress(Address address);
}
