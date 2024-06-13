package com.turkcell.customerservice.services.dtos.response;

import com.turkcell.customerservice.entities.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerGetResponse {
    private int id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String nationalityId;
    private String accountNumber;
    private String gsmNumber;
    private String orderNumber;
    private String motherName;
    private String fatherName;
    private Gender gender;
    private LocalDate birthDate;
    private int defaultAddressId;
}
