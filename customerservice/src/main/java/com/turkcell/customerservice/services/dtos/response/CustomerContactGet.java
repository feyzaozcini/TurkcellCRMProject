package com.turkcell.customerservice.services.dtos.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerContactGet {
    private int id;
    private String email;
    private String mobilePhone;
    private String homePhone;
    private String fax;
}
