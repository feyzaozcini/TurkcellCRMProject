package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.services.dtos.request.AddressUpdateRequest;
import com.turkcell.customerservice.services.dtos.response.CustomerAddressGet;

public interface AddressService {
    void updateAddress(AddressUpdateRequest request);
    CustomerAddressGet getAddressById(int id);
}
