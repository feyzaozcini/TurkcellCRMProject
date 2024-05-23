package com.turkcell.customerservice.controllers;
import com.turkcell.customerservice.repositories.CustomerRepository;
import com.turkcell.customerservice.services.abstracts.CustomerService;
import com.turkcell.customerservice.services.dtos.request.*;
import com.turkcell.customerservice.services.dtos.response.CustomerAddressGet;
import com.turkcell.customerservice.services.dtos.response.CustomerContactGet;
import com.turkcell.customerservice.services.dtos.response.CustomerGetResponse;
import com.turkcell.customerservice.services.dtos.response.SearchResponse;
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
    @PutMapping("/update")
    public void updateCustomer(@RequestBody CustomerUpdateRequest request){
        customerService.updateCustomerById(request);
    }

    @PutMapping("/addAddress")
    public void addAddressToCustomer(@RequestBody CustomerAddressAddRequest dto){
        customerService.addAddressToCustomer(dto);
    }

    @GetMapping("/addresses")
    public List<CustomerAddressGet> getCustomerAdressesById(@RequestParam int customerId){
        return customerService.getCustomerAdressesByCustomerId(customerId);
    }

    @DeleteMapping("/deleteAddress")
    public void deleteCustomerAddressByAddressId(@RequestParam int addressId){
        customerService.deleteCustomerAddressByAddressId(addressId);
    }
    @DeleteMapping("/deleteContact")
    public void deleteCustomerContactByContactId(@RequestParam int contactId){
        customerService.deleteCustomerContactByContactId(contactId);
    }
    @PutMapping("/addContact")
    public void addContactToCustomer(@RequestBody CustomerContactAdd request){
        customerService.addContactToCustomer(request);
    }

    @GetMapping("/contacts")
    public List<CustomerContactGet> getCustomerContactsById(@RequestParam int customerId){
        return customerService.getCustomerContactsByCustomerId(customerId);
    }

    @PostMapping("/search")
    public List<SearchResponse> searchCustomer(@RequestBody SearchRequest request){
        return customerService.searchCustomer(request);
    }
}
