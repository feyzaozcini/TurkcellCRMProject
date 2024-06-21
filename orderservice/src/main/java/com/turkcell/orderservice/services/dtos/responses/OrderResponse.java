package com.turkcell.orderservice.services.dtos.responses;
import com.turkcell.orderservice.clients.dtos.customerservice.AddressResponse;
import com.turkcell.orderservice.clients.dtos.productservice.ProductGetResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private int id;
    private int customerId;
    private int accountId;
    private AddressResponse serviceAddress;
    private List<OrderProductResponse> products;
    private float totalAmount;
}
