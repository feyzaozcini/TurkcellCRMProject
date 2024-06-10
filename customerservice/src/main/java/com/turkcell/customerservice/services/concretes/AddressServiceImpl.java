package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.core.utils.types.BusinessException;
import com.turkcell.customerservice.core.utils.types.NotFoundException;
import com.turkcell.customerservice.entities.Address;
import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.entities.IndividualCustomer;
import com.turkcell.customerservice.repositories.AddressRepository;
import com.turkcell.customerservice.repositories.IndividualCustomerRepository;
import com.turkcell.customerservice.services.abstracts.AddressService;
import com.turkcell.customerservice.services.dtos.request.AddressUpdateRequest;
import com.turkcell.customerservice.services.dtos.request.CustomerSetDefaultAddress;
import com.turkcell.customerservice.services.dtos.request.IndividualCustomerAddressAddRequest;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerAddressGet;
import com.turkcell.customerservice.services.mappers.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final IndividualCustomerRepository individualCustomerRepository;

    public void updateAddress(AddressUpdateRequest request) {
        isExist(request.getId());
        Address address = addressRepository.findById(request.getId()).orElseThrow();
        AddressMapper.INSTANCE.addressFromUpdateRequest(request, address);
        Customer customer = addressRepository.findById(request.getId()).orElseThrow(() -> new NotFoundException("Address not found with id: " + request.getId())).getCustomer();
        address.setUpdatedDate(LocalDateTime.now());
        address.setCustomer(customer);
        addressRepository.save(address);
    }

    public IndividualCustomerAddressGet getAddressById(int id) {
        isExist(id);
        return AddressMapper.INSTANCE.getResponseFromAddress(addressRepository.findById(id).orElseThrow());
    }

    @Override
    public void addAddressToIndividualCustomer(IndividualCustomerAddressAddRequest request) {
        Address address = AddressMapper.INSTANCE.addressFromAddRequest(request);
        address.setCreatedDate(LocalDateTime.now());
        address.setActive(true);
        IndividualCustomer customer = individualCustomerRepository.findById(request.getCustomerId()).filter(IndividualCustomer::getActive)
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + request.getCustomerId()));
        address.setCustomer(customer);
        addressRepository.save(address);
    }

    @Override
    public List<IndividualCustomerAddressGet> getAllAdressesByCustomerId(int id) throws RuntimeException {
        return individualCustomerRepository.findById(id).orElseThrow(() -> new BusinessException("Customer not found with id: " + id))
                .getAddresses().stream()
                .filter(Address::getActive)
                .map(AddressMapper.INSTANCE::getResponseFromAddress)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAddressByAddressId(int addressId) {
        isExist(addressId);
        Address address = addressRepository.findById(addressId).orElseThrow();
        address.setActive(false);
        address.setDeletedDate(LocalDateTime.now());
        addressRepository.save(address);
    }

    @Override
    public void setDefaultAddressToIndividualCustomer(CustomerSetDefaultAddress request) {
        IndividualCustomer individualCustomer = individualCustomerRepository.findById(request.getCustomerId()).orElseThrow(() -> new NotFoundException("Customer not found with id: " + request.getCustomerId()));
        individualCustomer.setDefaultAddress(addressRepository.findById(request.getAddressId()).orElseThrow(() -> new NotFoundException("Address not found with id: " + request.getAddressId())));
        individualCustomerRepository.save(individualCustomer);
    }

    public void isExist(int id){
        if(!addressRepository.existsById(id) || !addressRepository.findById(id).orElseThrow().getActive()){
            throw new NotFoundException("No Address found with id: " + id);
        }
    }
}
