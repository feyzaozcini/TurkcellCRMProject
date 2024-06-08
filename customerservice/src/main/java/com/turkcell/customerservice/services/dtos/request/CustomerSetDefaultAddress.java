package com.turkcell.customerservice.services.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSetDefaultAddress {
    private int customerId;
    private int addressId;
}
