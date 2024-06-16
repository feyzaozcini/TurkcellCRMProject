package com.turkcell.basketservice.services.abstracts;

import com.turkcell.basketservice.services.dtos.requests.BasketAddItemRequest;
import com.turkcell.basketservice.services.dtos.requests.BasketCreateRequest;
import com.turkcell.basketservice.services.dtos.requests.BasketDeleteItemRequest;

public interface BasketService {
    void createCart(BasketCreateRequest request);
    void addItemToCart(BasketAddItemRequest request);
    void deleteItemFromCart(BasketDeleteItemRequest request);
    void clearCartById(int cartId);
    void deleteCartById(int cartId);
}
