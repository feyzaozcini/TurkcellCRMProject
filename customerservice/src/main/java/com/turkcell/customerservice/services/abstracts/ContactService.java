package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.services.dtos.request.ContactUpdateRequest;
import com.turkcell.customerservice.services.dtos.request.IndividualCustomerContactAdd;
import com.turkcell.customerservice.services.dtos.response.CreatedContactResponse;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerContactGet;
import com.turkcell.customerservice.services.dtos.response.UpdatedContactResponse;

import java.util.List;

public interface ContactService {
    UpdatedContactResponse updateContact(ContactUpdateRequest request);

    CreatedContactResponse addContactToIndividualCustomer(IndividualCustomerContactAdd request);

    List<IndividualCustomerContactGet> getIndividualCustomerContactsByCustomerId(int customerId);

    void deleteContactByContactId(int contactId);

    IndividualCustomerContactGet getContactById(int id);
}
