package com.turkcell.accountservice.services.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountAddRequest {
    private Integer customerId;
    private String accountName;
    private String description;
    private Integer addressId;
}
