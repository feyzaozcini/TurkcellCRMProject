package com.turkcell.customerservice.services.rules;

import com.turkcell.customerservice.core.utils.types.NotFoundException;
import com.turkcell.customerservice.repositories.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactBusinessRules {
    private final ContactRepository contactRepository;

    public void isContactExist(int id){
        if(!contactRepository.existsById(id) || !contactRepository.findById(id).orElseThrow().getActive()){
            throw new NotFoundException("No Contact found with id: " + id);
        }
    }
}
