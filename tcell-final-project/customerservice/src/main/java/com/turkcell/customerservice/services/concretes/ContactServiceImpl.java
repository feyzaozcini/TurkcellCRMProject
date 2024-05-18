package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.entities.Contact;
import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.repositories.ContactRepository;
import com.turkcell.customerservice.services.abstracts.ContactService;
import com.turkcell.customerservice.services.dtos.request.ContactUpdateRequest;
import com.turkcell.customerservice.services.mappers.ContactMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    public void updateContact(ContactUpdateRequest request) {
        controlIsExist(request.getId());
        Contact contact = ContactMapper.INSTANCE.contactFromUpdateRequest(request);
        Customer customer = contactRepository.findById(request.getId()).orElseThrow().getCustomer();
        contact.setCustomer(customer);
        contactRepository.save(contact);
    }
    public void controlIsExist(int id){
        if(!contactRepository.existsById(id))
            throw new RuntimeException(" ");
    }
}
