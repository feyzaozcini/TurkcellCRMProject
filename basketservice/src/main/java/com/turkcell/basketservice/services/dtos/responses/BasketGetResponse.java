package com.turkcell.basketservice.services.dtos.responses;

import com.turkcell.basketservice.services.dtos.requests.BasketItemDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketGetResponse {
    private int id;
    private int accountId;
    private double totalAmount;
    private List<BasketItemDetails> items;
}
