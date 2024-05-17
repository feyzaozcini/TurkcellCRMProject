package com.turkcell.customerservice.services.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddressGet {
    private int id;
    private String city;
    private String district;
    private String street;
    private String addressDescription;

    private String houseFlatNumberStreet;
}
