package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.entities.Address;
import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.repositories.AddressRepository;
import com.turkcell.customerservice.repositories.CustomerRepository;
import com.turkcell.customerservice.services.abstracts.CustomerService;
import com.turkcell.customerservice.services.dtos.request.CustomerAddRequest;
import com.turkcell.customerservice.services.dtos.request.CustomerAddressAdd;
import com.turkcell.customerservice.services.dtos.request.CustomerUpdateRequest;
import com.turkcell.customerservice.services.dtos.response.CustomerAddressesGetResponse;
import com.turkcell.customerservice.services.dtos.response.CustomerGetResponse;
import com.turkcell.customerservice.services.mappers.AddressMapper;
import com.turkcell.customerservice.services.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    @Override
    public void addCustomer(CustomerAddRequest request) {
        Customer newCustomer = CustomerMapper.INSTANCE.getCustomerFromAddRequest(request);
        System.out.println(newCustomer.getFirstName());
        customerRepository.save(newCustomer);
    }

    @Override
    public CustomerGetResponse getCustomerById(int id) {
        return CustomerMapper.INSTANCE.getResponseFromCustomer(customerRepository.findById(id).orElseThrow());
    }

    @Override
    public List<CustomerGetResponse> getCustomers() {
       return customerRepository.findAll().stream()
                .map((customer)-> CustomerMapper.INSTANCE.getResponseFromCustomer(customer))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCustomerById(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void updateCustomerById(int id, CustomerUpdateRequest request) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer = CustomerMapper.INSTANCE.customerFromUpdateRequest(request);
        customer.setId(customer.getId());
        customerRepository.save(customer);
    }

    @Override
    public void addCustomerAddress(int id, CustomerAddressAdd request) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        Address address=addressRepository.save(AddressMapper.INSTANCE.addressFromAddRequest(request));
        List<Address> customerAddresses = customer.getAddresses();
        customerAddresses.add(address);
        customer.setAddresses(customerAddresses);
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerAddressesGetResponse> getCustomerAdressesById(int id) throws RuntimeException {
        List<CustomerAddressesGetResponse> addresses = customerRepository.findById(id).orElseThrow(()-> new RuntimeException("YOKKK!!")).getAddresses().stream()
                .map((response)->AddressMapper.INSTANCE.getResponseFromAddress(response))
                .collect(Collectors.toList());
        return addresses;
    }

    @Override
    public void deleteCustomerAddressByAddressId(int addressId) {
//        Customer customer = customerRepository.findById(customerId).orElseThrow();
//        Address address = addressRepository.findById(addressId).orElseThrow();
//        List<Address> customerAddresses = customer.getAddresses();
//        customerAddresses.remove(address);
//        customer.setAddresses(customerAddresses);
//        customer.setId(customerId);
//        customerRepository.save(customer);
        addressRepository.deleteById(addressId);
    }


}
