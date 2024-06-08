package com.turkcell.authserver.services.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {
    private String token;
    @Max(value = 1000000)
    private int customerId;
    private String firstName;
    private String lastName;
    @Digits(integer = 11, fraction = 0)
    @Positive
    private Long nationalityId;
    @Positive
    private Long accountNumber;
    @Digits(integer = 11, fraction = 0)
    @Positive
    private Long gsmNumber;
    @Positive
    private Long orderNumber;
}
