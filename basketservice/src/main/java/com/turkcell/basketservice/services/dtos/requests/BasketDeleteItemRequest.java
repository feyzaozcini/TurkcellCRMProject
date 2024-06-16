package com.turkcell.basketservice.services.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketDeleteItemRequest {
    private int accountId;
    private BasketItemDetails item;
}