package com.turkcell.orderservice.services.dtos.responses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private int id;
    private AddressResponse serviceAddress;
    private float totalAmount;
    private int customerId;
    private Set<Integer> productIds;
}
