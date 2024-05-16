package com.turkcell.customerservice.services.dtos.request;
import com.turkcell.customerservice.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerContactAdd {
    private int customerId;
    private String email;
    private String mobilePhone;
    private String homePhone;
    private String fax;
}
