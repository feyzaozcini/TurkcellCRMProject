package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.entities.Address;
import com.turkcell.customerservice.entities.Contact;
import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.repositories.AddressRepository;
import com.turkcell.customerservice.repositories.ContactRepository;
import com.turkcell.customerservice.repositories.CustomerRepository;
import com.turkcell.customerservice.services.abstracts.CustomerService;
import com.turkcell.customerservice.services.dtos.request.CustomerAddRequest;
import com.turkcell.customerservice.services.dtos.request.CustomerAddressAddRequest;
import com.turkcell.customerservice.services.dtos.request.CustomerContactAdd;
import com.turkcell.customerservice.services.dtos.request.CustomerUpdateRequest;
import com.turkcell.customerservice.services.dtos.response.CustomerAddressGet;
import com.turkcell.customerservice.services.dtos.response.CustomerContactGet;
import com.turkcell.customerservice.services.dtos.response.CustomerGetResponse;
import com.turkcell.customerservice.services.mappers.AddressMapper;
import com.turkcell.customerservice.services.mappers.ContactMapper;
import com.turkcell.customerservice.services.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;
    @Override
    public void addCustomer(CustomerAddRequest request) {
        Customer newCustomer = CustomerMapper.INSTANCE.getCustomerFromAddRequest(request);
        customerRepository.save(newCustomer);
    }

    @Override
    public CustomerGetResponse getCustomerById(int id) {
        return CustomerMapper.INSTANCE.getResponseFromCustomer(customerRepository.findById(id).orElseThrow());
    }

    @Override
    public List<CustomerGetResponse> getCustomers() {
       return customerRepository.findAll().stream()
                .map(CustomerMapper.INSTANCE::getResponseFromCustomer)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCustomerById(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void updateCustomerById(CustomerUpdateRequest request) {
        boolean isExist = customerRepository.existsById(request.getId());
        if(isExist){
            Customer customer = CustomerMapper.INSTANCE.customerFromUpdateRequest(request);
            customerRepository.save(customer);
        }
        else{
            throw new RuntimeException("Yok");
        }
    }

    @Override
    public void addAddressToCustomer(CustomerAddressAddRequest request) {
        Address address = AddressMapper.INSTANCE.addressFromAddRequest(request);
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow();
        Set<Address> customerAddresses = customer.getAddresses();
        customerAddresses.add(address);
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerAddressGet> getCustomerAdressesByCustomerId(int id) throws RuntimeException {
        return customerRepository.findById(id).orElseThrow(()-> new RuntimeException("YOKKK!!")).getAddresses().stream()
                .map(AddressMapper.INSTANCE::getResponseFromAddress)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCustomerAddressByAddressId(int addressId) {
        boolean isAddressExist = addressRepository.existsById(addressId);
        if(isAddressExist){
            addressRepository.deleteById(addressId);
        }
        else{
            throw new RuntimeException(" ");
        }
    }

    public void addContactToCustomer(CustomerContactAdd request){
        Contact contact = ContactMapper.INSTANCE.contactFromAddRequest(request);
        if(customerRepository.existsById(request.getCustomerId())){
            Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow();
            Set<Contact> customerContacts = customer.getContacts();
            customerContacts.add(contact);
            customer.setContacts(customerContacts);
            customerRepository.save(customer);
        }
        else{
            throw new RuntimeException(" ");
        }

    }
    public List<CustomerContactGet> getCustomerContactsByCustomerId(int customerId){
        return customerRepository.findById(customerId).orElseThrow(()-> new RuntimeException("YOKKK!!")).getContacts()
                .stream()
                .map((contact) -> ContactMapper.INSTANCE.getResponseFromContact(contact)
        ).collect(Collectors.toList());

    }
    @Override
    public void deleteCustomerContactByContactId(int contactId) {
        boolean isAddressExist = contactRepository.existsById(contactId);
        if(isAddressExist){
            contactRepository.deleteById(contactId);
        }
        else{
            throw new RuntimeException(" ");
        }
    }


}
