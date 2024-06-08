package com.turkcell.orderservice.services.dtos.responses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private int id;
    private int customerId;
    private AddressResponse serviceAddress;
    private List<ProductGetResponse> products;
    private float totalAmount;
}
