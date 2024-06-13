package com.turkcell.customerservice.services.dtos.request;

import com.turkcell.customerservice.entities.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerUpdateRequest {
    @NotNull
    private int id;

    @NotBlank(message = "{firstName.NotBlank}")
    @Size(min = 2, max = 50, message = "{firstName.Size}")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9a-zA-Z])[a-zA-Z0-9]*$", message = "{name.Pattern}")
    private String firstName;

    @Size(min = 2, max = 50, message = "{secondName.Size}")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9a-zA-Z])[a-zA-Z0-9]*$", message = "{name.Pattern}")
    private String secondName;

    @Size(min = 2, max = 50, message = "{lastName.Size}")
    @NotBlank(message = "{lastName.NotBlank}")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9a-zA-Z])[a-zA-Z0-9]*$", message = "{name.Pattern}")
    private String lastName;

    @Size(min = 11, max = 11, message = "{nationalityId.Size}")
    @Pattern(regexp = "^[0-9]{11}$", message = "{nationalityId.Pattern}")
    private String nationalityId;

    private String accountNumber;
    private String gsmNumber;
    private String orderNumber;

    @Size(min = 2, max = 50, message = "{motherName.Size}")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9a-zA-Z])[a-zA-Z0-9]*$", message = "{name.Pattern}")
    private String motherName;

    @Size(min = 2, max = 50, message = "{fatherName.Size}")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9a-zA-Z])[a-zA-Z0-9]*$", message = "{name.Pattern}")
    private String fatherName;

    @NotNull(message = "{gender.NotBlank}")
    private Gender gender;

    @NotNull(message = "{birthDate.NotBlank}")
    private LocalDate birthDate;
}
