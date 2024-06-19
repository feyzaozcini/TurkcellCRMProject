package com.turkcell.basketservice.services.abstracts;

import com.turkcell.basketservice.services.dtos.requests.BasketAddItemRequest;
import com.turkcell.basketservice.services.dtos.requests.BasketCreateRequest;
import com.turkcell.basketservice.services.dtos.requests.BasketDeleteItemRequest;
import com.turkcell.basketservice.services.dtos.responses.BasketGetResponse;

public interface BasketService {
    BasketGetResponse createBasket(BasketCreateRequest request);
    void addItemToBasket(BasketAddItemRequest request);
    void deleteItemFromBasket(BasketDeleteItemRequest request);
    void clearBasketById(int basketId);
    void deleteBasketById(int basketId);
    BasketGetResponse getBasketByAccountId(int accountId);
    BasketGetResponse getBasketById(int basketId);
}
