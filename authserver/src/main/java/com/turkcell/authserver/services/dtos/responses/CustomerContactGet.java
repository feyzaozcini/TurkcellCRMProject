package com.turkcell.authserver.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerContactGet {
    private int id;
    private String email;
    private String mobilePhone;
    private String homePhone;
    private String fax;
}
