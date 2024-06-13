package com.turkcell.customerservice.services.rules;

import com.turkcell.customerservice.core.utils.types.NotFoundException;
import com.turkcell.customerservice.repositories.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressBusinessRules {
    private final AddressRepository addressRepository;

    public void isAddressExist(int id){
        if(!addressRepository.existsById(id) || !addressRepository.findById(id).orElseThrow().getActive()){
            throw new NotFoundException("No Address found with id: " + id);
        }
    }
}
