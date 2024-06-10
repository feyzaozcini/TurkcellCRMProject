package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.services.abstracts.AddressService;
import com.turkcell.customerservice.services.dtos.request.AddressUpdateRequest;
import com.turkcell.customerservice.services.dtos.request.CustomerSetDefaultAddress;
import com.turkcell.customerservice.services.dtos.request.IndividualCustomerAddressAddRequest;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerAddressGet;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PutMapping("/update")
    public void updateAddress(@RequestBody AddressUpdateRequest request) {
        addressService.updateAddress(request);
    }

    @GetMapping("/{id}")
    public IndividualCustomerAddressGet getAddressById(@PathVariable int id) {
        return addressService.getAddressById(id);
    }

    @PostMapping("/add")
    public void addAddress(@RequestBody IndividualCustomerAddressAddRequest request){
        addressService.addAddressToIndividualCustomer(request);
    }

    @GetMapping("/addresses")
    public List<IndividualCustomerAddressGet> getAllAddressesByCustomerId(@RequestParam int customerId) {
        return addressService.getAllAdressesByCustomerId(customerId);
    }

    @DeleteMapping("/deleteAddress")
    public void deleteAddressByAddressId(@RequestParam int addressId) {
        addressService.deleteAddressByAddressId(addressId);
    }

    @PostMapping("/setDefaultAddressToCustomer")
    public void setDefaultAddressToIndividualCustomer(@RequestBody CustomerSetDefaultAddress request) {
        addressService.setDefaultAddressToIndividualCustomer(request);
    }
}
