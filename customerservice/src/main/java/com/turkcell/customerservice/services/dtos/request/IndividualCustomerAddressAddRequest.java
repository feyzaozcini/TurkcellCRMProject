package com.turkcell.customerservice.services.dtos.request;

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
public class IndividualCustomerAddressAddRequest {
    @NotNull
    private int customerId;

    @NotBlank(message = "{city.NotBlank}")
    private String city;

    @NotBlank(message = "{district.NotBlank}")
    private String district;

    @NotBlank(message = "{street.NotBlank}")
    private String street;

    @NotBlank(message = "{addressDescription.NotBlank}")
    private String addressDescription;

    @NotBlank(message = "{houseFlatNumberStreet.NotBlank}")
    private String houseFlatNumberStreet;
}
