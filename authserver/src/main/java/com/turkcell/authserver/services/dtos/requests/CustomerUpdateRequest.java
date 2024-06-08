package com.turkcell.authserver.services.dtos.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {
    private int id;
    @NotBlank
    @Size(max = 50)
    private String firstName;
    @NotBlank
    @Size(max = 50)
    private String secondName;
    @NotBlank
    @Size(max = 50)
    private String lastName;
    @NotBlank
    @Positive
    @Max(value = 10000000000L)
    private Long nationalityId;
    private Long accountNumber;
    private Long gsmNumber;
    private Long orderNumber;
    @NotBlank
    @Size(max = 50)
    private String motherName;
    @NotBlank
    @Size(max = 50)
    private String fatherName;
    @NotBlank
    private String gender;
    @NotBlank
    private LocalDateTime birthDate;
}
