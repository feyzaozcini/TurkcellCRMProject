package com.turkcell.basketservice.clients.dtos.accountservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountGetResponse {
    private String accountNumber;
    private String accountName;
    private String description;
    private AccountType accountType;
    private AccountStatus status;
    private List<Integer> productIds;
    private Integer addressId;
    private Integer customerId;
    @Getter
    public enum AccountType{
        CREDIT,
        BILLING,
        PAYMENT,
        DEBIT
    }

    @Getter
    public enum AccountStatus{
        ACTIVE,
        INACTIVE,
        BLOCKED,
        SUSPENDED,
        DELETED
    }
}