package com.turkcell.customerservice.services.concretes;

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
import com.turkcell.customerservice.services.rules.AddressBusinessRules;
import com.turkcell.customerservice.services.rules.IndividualCustomerBusinessRules;
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
    private final AddressBusinessRules addressBusinessRules;
    private final IndividualCustomerBusinessRules individualCustomerBusinessRules;

    public void updateAddress(AddressUpdateRequest request) {
        addressBusinessRules.isAddressExist(request.getId());
        Address address = addressRepository.findById(request.getId()).orElseThrow();
        AddressMapper.INSTANCE.addressFromUpdateRequest(request, address);
        Customer customer = addressRepository.findById(request.getId()).orElseThrow().getCustomer();
        address.setUpdatedDate(LocalDateTime.now());
        address.setCustomer(customer);
        addressRepository.save(address);
    }

    public IndividualCustomerAddressGet getAddressById(int id) {
        addressBusinessRules.isAddressExist(id);
        return AddressMapper.INSTANCE.getResponseFromAddress(addressRepository.findById(id).orElseThrow());
    }

    @Override
    public void addAddressToIndividualCustomer(IndividualCustomerAddressAddRequest request) {
        individualCustomerBusinessRules.isIndividualCustomerExist(request.getCustomerId());
        Address address = AddressMapper.INSTANCE.addressFromAddRequest(request);
        address.setCreatedDate(LocalDateTime.now());
        address.setActive(true);
        IndividualCustomer customer = individualCustomerRepository.findById(request.getCustomerId()).filter(IndividualCustomer::getActive).orElseThrow();
        address.setCustomer(customer);
        addressRepository.save(address);
    }

    @Override
    public List<IndividualCustomerAddressGet> getAllAdressesByCustomerId(int id) throws RuntimeException {
        individualCustomerBusinessRules.isIndividualCustomerExist(id);
        return individualCustomerRepository.findById(id).orElseThrow()
                .getAddresses().stream()
                .filter(Address::getActive)
                .map(AddressMapper.INSTANCE::getResponseFromAddress)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAddressByAddressId(int addressId) {
        addressBusinessRules.isAddressExist(addressId);
        addressBusinessRules.isCustomerHasAtLeastOneAddress(addressId);
        addressBusinessRules.isAddressDefault(addressId);
        Address address = addressRepository.findById(addressId).orElseThrow();
        address.setActive(false);
        address.setDeletedDate(LocalDateTime.now());
        addressRepository.save(address);
    }

    @Override
    public void setDefaultAddressToIndividualCustomer(CustomerSetDefaultAddress request) {
        addressBusinessRules.isAddressExist(request.getAddressId());
        individualCustomerBusinessRules.isIndividualCustomerExist(request.getCustomerId());
        IndividualCustomer individualCustomer = individualCustomerRepository.findById(request.getCustomerId()).orElseThrow();
        individualCustomer.setDefaultAddress(addressRepository.findById(request.getAddressId()).orElseThrow());
        individualCustomerRepository.save(individualCustomer);
    }
}
