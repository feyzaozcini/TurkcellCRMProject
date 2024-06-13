package com.turkcell.customerservice.services.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndividualCustomerSearchResponse {
    private int customerId;
    private String firstName;
    private String secondName;
    private String lastName;
    private String nationalityId;
}
