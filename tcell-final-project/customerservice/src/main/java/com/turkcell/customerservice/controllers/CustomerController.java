package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.services.abstracts.CustomerService;
import com.turkcell.customerservice.services.dtos.request.CustomerAddRequest;
import com.turkcell.customerservice.services.dtos.request.CustomerAddressAdd;
import com.turkcell.customerservice.services.dtos.request.CustomerUpdateRequest;
import com.turkcell.customerservice.services.dtos.response.CustomerAddressesGetResponse;
import com.turkcell.customerservice.services.dtos.response.CustomerGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/all")
    public List<CustomerGetResponse> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public CustomerGetResponse getCustomer(@PathVariable int id){
        return customerService.getCustomerById(id);
    }
    @PostMapping("/add")
    public void addCustomer(@RequestBody CustomerAddRequest customer){
        customerService.addCustomer(customer);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomerById(@PathVariable int id){
        customerService.deleteCustomerById(id);
    }
    @PutMapping("/update/{id}")
    public void updateCustomer(@PathVariable int id, @RequestBody CustomerUpdateRequest request){
        customerService.updateCustomerById(id, request);
    }

    @PutMapping("/update/address")
    public void updateCustomerAddresses(@RequestParam int customerId, @RequestBody CustomerAddressAdd dto){
        customerService.addCustomerAddress(customerId, dto);
    }

    @GetMapping("/adresses")
    public List<CustomerAddressesGetResponse> getCustomerAdressesById(@RequestParam int customerId){
        return customerService.getCustomerAdressesById(customerId);
    }

    @DeleteMapping("/deleteAddress")
    public void deleteAddress(@RequestParam int addressId){
        customerService.deleteCustomerAddressByAddressId(addressId);
    }
}
