package com.turkcell.customerservice.services.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatedAddressResponse {
    private int id;

    private int customerId;

    private String city;

    private String district;

    private String street;

    private String addressDescription;

    private String houseFlatNumberStreet;
}
