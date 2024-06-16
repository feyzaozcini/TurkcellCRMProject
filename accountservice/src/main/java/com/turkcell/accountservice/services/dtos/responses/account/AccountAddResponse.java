package com.turkcell.accountservice.services.dtos.responses.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountAddResponse {
    private Integer id;
    private Integer customerId;
    private String accountName;
    private String description;
    private Integer addressId;
}
