package com.turkcell.catalogservice.services.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedProductResponse {
    private Integer id;

    private String name;

    private Float price;

    private Integer catalogId;
}
