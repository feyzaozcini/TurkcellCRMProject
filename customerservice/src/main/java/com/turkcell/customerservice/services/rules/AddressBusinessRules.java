package com.turkcell.customerservice.services.rules;

import com.turkcell.customerservice.core.utils.types.BusinessException;
import com.turkcell.customerservice.core.utils.types.NotFoundException;
import com.turkcell.customerservice.entities.Address;
import com.turkcell.customerservice.entities.IndividualCustomer;
import com.turkcell.customerservice.repositories.AddressRepository;
import com.turkcell.customerservice.repositories.IndividualCustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressBusinessRules {
    private final AddressRepository addressRepository;
    private final IndividualCustomerRepository individualCustomerRepository;

    public void isAddressExist(int id){
        if(!addressRepository.existsById(id) || !addressRepository.findById(id).orElseThrow().getActive()){
            throw new NotFoundException("No Address found with id: " + id);
        }
    }

    public void isCustomerHasAtLeastOneAddress(int addressId){
        Address address = addressRepository.findById(addressId).orElseThrow();

        int customerId = address.getCustomer().getId();
        int addressCount = addressRepository.countByCustomerId(customerId);

        if (addressCount <= 1) {
            throw new BusinessException("Customer should have at least one address.");
        }
    }

    public void isAddressDefault(int addressId){
        Address address = addressRepository.findById(addressId).orElseThrow();
        int customerId = address.getCustomer().getId();
        IndividualCustomer individualCustomer = individualCustomerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("No customer found id: " + customerId));
        if(addressId == individualCustomer.getDefaultAddress().getId()){
            throw new BusinessException("The address that you want to delete is a default address. Please, change default address then try again.");
        }
    }
}
