package com.turkcell.customerservice.services.dtos.request;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerSearchRequest {
    @Min(value = 1, message = "{customerId.MinSize}")
    @Max(value = 9999999, message = "{customerId.MaxSize}")
    private int customerId;

    private String firstName;
    private String lastName;

    @Size(min = 11, max = 11, message = "{nationalityId.Size}")
    @Pattern(regexp = "^[0-9]{11}$", message = "{nationalityId.Pattern}")
    private String nationalityId;

    @Pattern(regexp = "^[0-9]+$", message = "{accountNumber.Pattern}")
    private String accountNumber;

    @Size(min = 11, max = 11, message = "{gsmNumber.Size}")
    @Pattern(regexp = "^[0-9]{11}$", message = "{gsmNumber.Pattern}")
    private String gsmNumber;

    @Pattern(regexp = "^[0-9]+$", message = "{orderNumber.Pattern}")
    private String orderNumber;
}
