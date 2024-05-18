package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.services.dtos.request.AddressUpdateRequest;

public interface AddressService {
    void updateAddress(AddressUpdateRequest request);
}
