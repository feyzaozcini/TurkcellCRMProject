package com.turkcell.customerservice.services.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdatedContactResponse {
    private int id;

    private int customerId;

    private String email;

    private String mobilePhone;

    private String homePhone;

    private String fax;
}
