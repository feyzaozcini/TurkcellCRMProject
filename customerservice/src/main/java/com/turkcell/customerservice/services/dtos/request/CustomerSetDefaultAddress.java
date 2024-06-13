package com.turkcell.customerservice.services.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSetDefaultAddress {
    @NotBlank(message = "{customerId.NotBlank}")
    private int customerId;
    @NotBlank(message = "{addressId.NotBlank}")
    private int addressId;
}
