package com.turkcell.customerservice.controllers;
import com.turkcell.customerservice.services.abstracts.AddressService;
import com.turkcell.customerservice.services.dtos.request.AddressUpdateRequest;
import com.turkcell.customerservice.services.dtos.response.CustomerAddressGet;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    @PutMapping("/update")
    public void updateAddress(AddressUpdateRequest request){
        addressService.updateAddress(request);
    }

    @GetMapping("/{id}")
    public CustomerAddressGet getAddressById(@PathVariable int addressId){
        return addressService.getAddressById(addressId);
    }
}
