package com.turkcell.catalogservice.services.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {

    private Integer id;

    private String name;

    private Float price;

    private Integer catalogId;

}
