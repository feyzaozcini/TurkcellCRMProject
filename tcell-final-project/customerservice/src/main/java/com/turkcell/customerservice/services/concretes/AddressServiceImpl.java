package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.core.utils.types.BusinessException;
import com.turkcell.customerservice.entities.Address;
import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.repositories.AddressRepository;
import com.turkcell.customerservice.services.abstracts.AddressService;
import com.turkcell.customerservice.services.dtos.request.AddressUpdateRequest;
import com.turkcell.customerservice.services.mappers.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    public void updateAddress(AddressUpdateRequest request) {
        controlIsExist(request.getId());
        Address address = AddressMapper.INSTANCE.addressFromUpdateRequest(request);
        Customer customer = addressRepository.findById(request.getId()).orElseThrow(()-> new BusinessException(request.getId() + ", bu idye sahip bir address bulunamadi")).getCustomer();
        address.setCustomer(customer);
        addressRepository.save(address);
    }

    public void controlIsExist(int id){
        if(!addressRepository.existsById(id)){
            throw new BusinessException(id + ", bu idye sahip bir adres bulunamadi");
        }
    }
}
