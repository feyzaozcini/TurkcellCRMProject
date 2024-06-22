package com.turkcell.orderservice.services.dtos.responses;
import com.turkcell.orderservice.clients.dtos.customerservice.AddressResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

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
