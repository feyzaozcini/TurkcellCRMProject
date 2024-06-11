package com.turkcell.accountservice.clients.dtos.catalogservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductGetDto {
    private int id;
    private String name;
    private float price;
    private int catalogId;
}
