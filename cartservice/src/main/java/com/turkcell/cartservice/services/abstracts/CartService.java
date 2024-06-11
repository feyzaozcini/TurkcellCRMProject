package com.turkcell.cartservice.services.abstracts;

import com.turkcell.cartservice.services.dtos.requests.CartAddItemRequest;
import com.turkcell.cartservice.services.dtos.requests.CartCreateRequest;
import com.turkcell.cartservice.services.dtos.requests.CartDeleteItemRequest;

public interface CartService {
    void createCart(CartCreateRequest request);
    void addItemToCart(CartAddItemRequest request);
    void deleteItemFromCart(CartDeleteItemRequest request);
    void clearCartById(int cartId);
    void deleteCartById(int cartId);
}
