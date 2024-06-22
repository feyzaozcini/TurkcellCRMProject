package com.turkcell.orderservice.services.dtos.requests;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    @NotNull
    private int customerId;
    @NotNull
    private int accountId;
    @NotNull
    private Map<Integer,Integer> productIds;
}
