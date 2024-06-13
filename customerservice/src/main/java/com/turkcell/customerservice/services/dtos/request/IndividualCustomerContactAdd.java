package com.turkcell.customerservice.services.dtos.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class IndividualCustomerContactAdd {
    private int customerId;

    @NotBlank(message = "{email.NotBlank}")
    @Email(message = "{email.isValid}")
    private String email;

    @NotBlank(message = "{mobilePhone.NotBlank}")
    @Size(min = 11, max = 11, message = "{mobilePhone.Size}")
    @Pattern(regexp = "^[1-9][0-9]{10}$", message = "{mobilePhone.Pattern}")
    private String mobilePhone;

    @Size(min = 11, max = 11, message = "{homePhone.Size}")
    @Pattern(regexp = "^[1-9][0-9]{10}$", message = "{homePhone.Pattern}")
    private String homePhone;

    @Size(min = 12, max = 12, message = "fax.Size")
    @Pattern(regexp = "^[1-9][0-9]{11}$", message = "fax.Pattern")
    private String fax;
}
