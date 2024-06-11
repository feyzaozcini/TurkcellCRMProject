package com.turkcell.customerservice.services.dtos.response;

import com.turkcell.customerservice.entities.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Long nationalityId;
    private Long accountNumber;
    private Long gsmNumber;
    private Long orderNumber;
    private String motherName;
    private String fatherName;
    private Gender gender;
    private LocalDateTime birthDate;
    private int defaultAddressId;
}
