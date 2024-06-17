package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.services.dtos.request.CustomerSetDefaultAddress;
import com.turkcell.customerservice.services.dtos.request.IndividualCustomerAddressAddRequest;
import com.turkcell.customerservice.services.dtos.request.AddressUpdateRequest;
import com.turkcell.customerservice.services.dtos.response.CreatedAddressResponse;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerAddressGet;
import com.turkcell.customerservice.services.dtos.response.UpdatedAddressResponse;

import java.util.List;

public interface AddressService {
    UpdatedAddressResponse updateAddress(AddressUpdateRequest request);
    IndividualCustomerAddressGet getAddressById(int id);
    CreatedAddressResponse addAddressToIndividualCustomer(IndividualCustomerAddressAddRequest request);
    List<IndividualCustomerAddressGet> getAllAdressesByCustomerId(int id);
    void deleteAddressByAddressId(int addressId);
    void setDefaultAddressToIndividualCustomer(CustomerSetDefaultAddress request);
}
