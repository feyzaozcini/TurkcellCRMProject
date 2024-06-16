package com.turkcell.basketservice.services.abstracts;

import com.turkcell.basketservice.services.dtos.requests.BasketAddItemRequest;
import com.turkcell.basketservice.services.dtos.requests.BasketCreateRequest;
import com.turkcell.basketservice.services.dtos.requests.BasketDeleteItemRequest;

public interface BasketService {
    void createBasket(BasketCreateRequest request);
    void addItemToBasket(BasketAddItemRequest request);
    void deleteItemFromBasket(BasketDeleteItemRequest request);
    void clearBasketById(int cartId);
    void deleteBasketById(int cartId);
}
