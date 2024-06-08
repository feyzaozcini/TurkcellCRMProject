package com.turkcell.customerservice.services.mappers;

import com.turkcell.customerservice.entities.Contact;
import com.turkcell.customerservice.services.dtos.request.ContactUpdateRequest;
import com.turkcell.customerservice.services.dtos.request.CustomerContactAdd;
import com.turkcell.customerservice.services.dtos.response.CustomerContactGet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    @Mapping(target = "customer.id", source = "customerId")
    Contact contactFromAddRequest(CustomerContactAdd request);
    CustomerContactGet getResponseFromContact(Contact contact);

    Contact contactFromUpdateRequest(ContactUpdateRequest request);
}
