package com.turkcell.customerservice.services.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AddressUpdateRequest {
    @Min(value = 1, message = "{addressId.MinValue}")
    private int id;

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
