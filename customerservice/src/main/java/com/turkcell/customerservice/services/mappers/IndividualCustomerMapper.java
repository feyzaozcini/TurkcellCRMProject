package com.turkcell.customerservice.services.mappers;

import com.turkcell.customerservice.entities.Address;
import com.turkcell.customerservice.entities.IndividualCustomer;
import com.turkcell.customerservice.services.dtos.request.IndividualCustomerAddRequest;
import com.turkcell.customerservice.services.dtos.request.IndividualCustomerUpdateRequest;
import com.turkcell.customerservice.services.dtos.response.CreatedIndividualCustomerResponse;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerAddressGet;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerGetResponse;
import com.turkcell.customerservice.services.dtos.response.UpdatedIndividualCustomerResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;


@Mapper
public interface IndividualCustomerMapper {
    IndividualCustomerMapper INSTANCE = Mappers.getMapper(IndividualCustomerMapper.class);

    @Mapping(target = "id", ignore = true)
    IndividualCustomer getIndividualCustomerFromAddRequest(IndividualCustomerAddRequest request);

    CreatedIndividualCustomerResponse getResponseFromCreatedIndividualCustomer(IndividualCustomer customer);

    @Mapping(target = "addresses", ignore = true)
    UpdatedIndividualCustomerResponse getResponseFromUpdatedIndividualCustomer(IndividualCustomer customer);

    default List<IndividualCustomerAddressGet> convertToAddressDtoList(List<Address> addresses) {
        return addresses.stream()
                .map(this::convertToAddressDto)
                .collect(Collectors.toList());
    }

    IndividualCustomerAddressGet convertToAddressDto(Address address);

    @Mapping(target = "defaultAddressId", source = "defaultAddress.id")
    IndividualCustomerGetResponse getResponseFromIndividualCustomer(IndividualCustomer individualCustomer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void individualCustomerFromUpdateRequest(IndividualCustomerUpdateRequest request, @MappingTarget IndividualCustomer individualCustomer);
}
