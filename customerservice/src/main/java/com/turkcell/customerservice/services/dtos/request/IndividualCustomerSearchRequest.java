package com.turkcell.customerservice.services.dtos.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerSearchRequest {
    @Max(value = 10000000)
    @Positive
    private int customerId;
    private String firstName;
    private String lastName;
    @Digits(integer = 11, fraction = 0)
    @Positive
    private Long nationalityId;
    @Positive
    private Long accountNumber;
    @Digits(integer = 11, fraction = 0)
    @Positive
    private Long gsmNumber;
    @Positive
    private Long orderNumber;
}
