package com.turkcell.cartservice.services.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartCreateRequest {
    @NotNull
    private int accountId;
    private List<CartItemDetails> items;
}
