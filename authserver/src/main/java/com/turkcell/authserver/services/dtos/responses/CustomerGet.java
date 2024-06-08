package com.turkcell.authserver.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerGet {
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
    private String gender;
    private LocalDateTime birthDate;

}
