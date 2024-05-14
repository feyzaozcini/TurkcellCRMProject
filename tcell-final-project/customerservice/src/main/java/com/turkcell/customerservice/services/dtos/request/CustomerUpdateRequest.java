package com.turkcell.customerservice.services.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {
    private String firstName;
    private String secondName;
    private String lastName;
    private Long nationalityId;
    private Long accountNumber;
    private Long gsmNumber;
    private Long orderNumber;
    private String motherName;
    private String fatherName;
    private String gender;
    private LocalDateTime birthDate;
}
