package com.turkcell.authserver.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {
    private int customerId;
    private String firstName;
    private String secondName;
    private String lastName;
    private Long nationalityId;

}
