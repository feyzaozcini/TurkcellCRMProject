package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.services.dtos.request.ContactUpdateRequest;
import com.turkcell.customerservice.services.dtos.request.IndividualCustomerContactAdd;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerContactGet;

import java.util.List;

public interface ContactService {
    void updateContact(ContactUpdateRequest request);

    void addContactToIndividualCustomer(IndividualCustomerContactAdd request);

    List<IndividualCustomerContactGet> getIndividualCustomerContactsByCustomerId(int customerId);

    void deleteContactByContactId(int contactId);

    IndividualCustomerContactGet getContactById(int id);
}
