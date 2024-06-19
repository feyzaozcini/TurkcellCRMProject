package com.turkcell.catalogservice.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedProductResponse {
    private int id;

    private String name;

    private float price;

    private int catalogId;
}
