package com.turkcell.customerservice.services.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSetDefaultAddress {
    @NotNull(message = "{customerId.NotBlank}")
    private int customerId;
    @NotNull(message = "{addressId.NotBlank}")
    private int addressId;
}
