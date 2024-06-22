package com.turkcell.orderservice.clients.dtos.customerservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerGetResponse {
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

    @Getter
    public enum Gender{
        MALE,
        FEMALE,
        NOT_SPECIFIED
    }
}
