package com.turkcell.accountservice.services.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdateRequest {
    private int id;
    private String accountNumber;
    private String accountName;
    private String description;
    private String accountType;
    private String status;
    private List<Integer> productIds;
    private Integer addressId;
    private Integer customerId;
}
