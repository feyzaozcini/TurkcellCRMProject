package com.turkcell.orderservice.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductResponse {
    private int id;
    private String name;
    private float price;
    private int catalogId;
    private int quantity;
}
