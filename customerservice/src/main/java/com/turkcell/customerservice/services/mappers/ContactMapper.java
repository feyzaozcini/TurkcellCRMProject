package com.turkcell.customerservice.services.mappers;

import com.turkcell.customerservice.entities.Contact;
import com.turkcell.customerservice.services.dtos.request.ContactUpdateRequest;
import com.turkcell.customerservice.services.dtos.request.IndividualCustomerContactAdd;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerContactGet;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    @Mapping(target = "customer.id", source = "customerId")
    Contact contactFromAddRequest(IndividualCustomerContactAdd request);
    IndividualCustomerContactGet getResponseFromContact(Contact contact);

    @Mapping(target = "customer", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Contact contactFromUpdateRequest(ContactUpdateRequest request, @MappingTarget Contact contact);
}
