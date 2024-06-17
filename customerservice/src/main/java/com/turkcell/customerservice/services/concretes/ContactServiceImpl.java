package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.core.utils.types.BusinessException;
import com.turkcell.customerservice.core.utils.types.NotFoundException;
import com.turkcell.customerservice.entities.Contact;
import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.entities.IndividualCustomer;
import com.turkcell.customerservice.repositories.ContactRepository;
import com.turkcell.customerservice.repositories.IndividualCustomerRepository;
import com.turkcell.customerservice.services.abstracts.ContactService;
import com.turkcell.customerservice.services.dtos.request.ContactUpdateRequest;
import com.turkcell.customerservice.services.dtos.request.IndividualCustomerContactAdd;
import com.turkcell.customerservice.services.dtos.response.CreatedContactResponse;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerContactGet;
import com.turkcell.customerservice.services.dtos.response.UpdatedContactResponse;
import com.turkcell.customerservice.services.mappers.ContactMapper;
import com.turkcell.customerservice.services.rules.ContactBusinessRules;
import com.turkcell.customerservice.services.rules.IndividualCustomerBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final IndividualCustomerRepository individualCustomerRepository;
    private final ContactBusinessRules contactBusinessRules;
    private final IndividualCustomerBusinessRules individualCustomerBusinessRules;

    public UpdatedContactResponse updateContact(ContactUpdateRequest request) {
        contactBusinessRules.isContactExist(request.getId());
        Contact contact = contactRepository.findById(request.getId()).orElseThrow();
        ContactMapper.INSTANCE.contactFromUpdateRequest(request, contact);
        Customer customer = contactRepository.findById(request.getId()).orElseThrow().getCustomer();
        contact.setCustomer(customer);
        contact.setUpdatedDate(LocalDateTime.now());
        Contact savedContact = contactRepository.save(contact);
        return ContactMapper.INSTANCE.getResponseFromUpdatedContact(savedContact);
    }


    public CreatedContactResponse addContactToIndividualCustomer(IndividualCustomerContactAdd request){
        individualCustomerBusinessRules.isIndividualCustomerExist(request.getCustomerId());
        Contact contact = ContactMapper.INSTANCE.contactFromAddRequest(request);
        contact.setCreatedDate(LocalDateTime.now());
        contact.setActive(true);
        IndividualCustomer customer = individualCustomerRepository.findById(request.getCustomerId()).orElseThrow();
        contact.setCustomer(customer);
        Contact savedContact = contactRepository.save(contact);
        return ContactMapper.INSTANCE.getResponseFromCreatedContact(savedContact);
    }

    @Override
    public List<IndividualCustomerContactGet> getIndividualCustomerContactsByCustomerId(int customerId){
        individualCustomerBusinessRules.isIndividualCustomerExist(customerId);
        return individualCustomerRepository.findById(customerId).orElseThrow()
                .getContacts()
                .stream()
                .filter(Contact::getActive)
                .map(ContactMapper.INSTANCE::getResponseFromContact)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteContactByContactId(int contactId) {
        contactBusinessRules.isContactExist(contactId);
        Contact contact = contactRepository.findById(contactId).orElseThrow();
        contact.setActive(false);
        contact.setDeletedDate(LocalDateTime.now());
        contactRepository.save(contact);
    }

    @Override
    public IndividualCustomerContactGet getContactById(int id) {
        contactBusinessRules.isContactExist(id);
        return ContactMapper.INSTANCE.getResponseFromContact(contactRepository.findById(id).orElseThrow());
    }
}
