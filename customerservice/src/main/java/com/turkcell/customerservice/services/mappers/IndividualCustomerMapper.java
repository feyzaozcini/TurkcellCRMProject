package com.turkcell.customerservice.services.mappers;

import com.turkcell.customerservice.entities.IndividualCustomer;
import com.turkcell.customerservice.services.dtos.request.IndividualCustomerAddRequest;
import com.turkcell.customerservice.services.dtos.request.IndividualCustomerUpdateRequest;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerGetResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.mapstruct.MappingTarget;


@Mapper
public interface IndividualCustomerMapper {
    IndividualCustomerMapper INSTANCE = Mappers.getMapper(IndividualCustomerMapper.class);

    @Mapping(target = "id", ignore = true)
    IndividualCustomer getIndividualCustomerFromAddRequest(IndividualCustomerAddRequest request);

    @Mapping(target = "defaultAddressId", source = "defaultAddress.id")
    IndividualCustomerGetResponse getResponseFromIndividualCustomer(IndividualCustomer individualCustomer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void individualCustomerFromUpdateRequest(IndividualCustomerUpdateRequest request, @MappingTarget IndividualCustomer individualCustomer);
}
