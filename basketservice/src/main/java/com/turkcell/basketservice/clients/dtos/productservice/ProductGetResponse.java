package com.turkcell.basketservice.clients.dtos.productservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductGetResponse {
    private int id;
    private String name;
    private float price;
    private int catalogId;
}
