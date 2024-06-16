package com.turkcell.customerservice.services.dtos.response;



import com.turkcell.customerservice.entities.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedIndividualCustomerResponse {
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
    private List<IndividualCustomerAddressGet> addresses;
    private List<IndividualCustomerContactGet> contacts;
    private LocalDateTime updatedDate;
}
