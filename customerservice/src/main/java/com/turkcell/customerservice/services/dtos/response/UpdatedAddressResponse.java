package com.turkcell.customerservice.services.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedAddressResponse {
    private int id;

    private int customerId;

    private String city;

    private String district;

    private String street;

    private String addressDescription;

    private String houseFlatNumberStreet;
}
