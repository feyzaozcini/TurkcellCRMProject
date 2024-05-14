package com.turkcell.customerservice.services.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddressAdd {
    private String city;
    private String district;
    private String street;
    private String addressDescription;

    private String houseFlatNumberStreet;

}
