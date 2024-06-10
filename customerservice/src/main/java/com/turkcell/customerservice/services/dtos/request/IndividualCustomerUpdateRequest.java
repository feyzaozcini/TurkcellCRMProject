package com.turkcell.customerservice.services.dtos.request;

import com.turkcell.customerservice.entities.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerUpdateRequest {
    @NotBlank
    private int id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String firstName;

    @Size(min = 2, max = 50)
    private String secondName;

    @Size(min = 2, max = 50)
    @NotBlank
    private String lastName;

    @Size(min = 11, max = 11)
    @Pattern(regexp = "^[0-9]{11}$")
    private Long nationalityId;

    private Long accountNumber;
    private Long gsmNumber;
    private Long orderNumber;
    @Size(min = 2, max = 50)
    private String motherName;
    @Size(min = 2, max = 50)
    private String fatherName;
    @NotBlank
    private Gender gender;

    @NotBlank
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/\\d{4}$")
    private LocalDateTime birthDate;
}
