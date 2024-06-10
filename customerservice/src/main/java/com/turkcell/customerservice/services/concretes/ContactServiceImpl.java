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
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerContactGet;
import com.turkcell.customerservice.services.mappers.ContactMapper;
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

    public void updateContact(ContactUpdateRequest request) {
        isExist(request.getId());
        Contact contact = contactRepository.findById(request.getId()).orElseThrow();
        ContactMapper.INSTANCE.contactFromUpdateRequest(request, contact);
        Customer customer = contactRepository.findById(request.getId()).orElseThrow(() -> new BusinessException(request.getId() + ", bu idye sahip bir contact bulunamadi!")).getCustomer();
        contact.setCustomer(customer);
        contact.setUpdatedDate(LocalDateTime.now());
        contactRepository.save(contact);
    }


    public void addContactToIndividualCustomer(IndividualCustomerContactAdd request){
        Contact contact = ContactMapper.INSTANCE.contactFromAddRequest(request);
        contact.setCreatedDate(LocalDateTime.now());
        contact.setActive(true);
        IndividualCustomer customer = individualCustomerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + request.getCustomerId()));
        contact.setCustomer(customer);
        contactRepository.save(contact);
    }

    @Override
    public List<IndividualCustomerContactGet> getIndividualCustomerContactsByCustomerId(int customerId){
        return individualCustomerRepository.findById(customerId).orElseThrow(()-> new NotFoundException("Customer not found with id: " + customerId))
                .getContacts()
                .stream()
                .filter(Contact::getActive)
                .map(ContactMapper.INSTANCE::getResponseFromContact)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteContactByContactId(int contactId) {
        isExist(contactId);
        Contact contact = contactRepository.findById(contactId).orElseThrow();
        contact.setActive(false);
        contact.setDeletedDate(LocalDateTime.now());
        contactRepository.save(contact);
    }

    @Override
    public IndividualCustomerContactGet getContactById(int id) {
        isExist(id);
        return ContactMapper.INSTANCE.getResponseFromContact(contactRepository.findById(id).orElseThrow());
    }

    public void isExist(int id){
        if(!contactRepository.existsById(id) || !contactRepository.findById(id).orElseThrow().getActive()){
            throw new NotFoundException("No Contact found with id: " + id);
        }
    }
}
