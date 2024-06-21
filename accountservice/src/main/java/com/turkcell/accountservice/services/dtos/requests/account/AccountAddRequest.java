package com.turkcell.accountservice.services.dtos.requests.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountAddRequest {
    @NotNull
    private Integer customerId;
    @NotBlank
    private String accountName;
    @NotBlank
    private String description;
    @NotNull
    private Integer addressId;
}
