package com.turkcell.customerservice.services.dtos.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerContactAdd {
    private int customerId;
    private String email;
    private String mobilePhone;
    private String homePhone;
    private String fax;
}
