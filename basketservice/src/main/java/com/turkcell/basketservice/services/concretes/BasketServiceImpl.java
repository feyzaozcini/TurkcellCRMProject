package com.turkcell.basketservice.services.concretes;

import com.turkcell.basketservice.services.abstracts.BasketService;
import com.turkcell.basketservice.services.dtos.requests.BasketAddItemRequest;
import com.turkcell.basketservice.services.dtos.requests.BasketCreateRequest;
import com.turkcell.basketservice.services.dtos.requests.BasketDeleteItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    @Override
    public void createBasket(BasketCreateRequest request) {

    }

    @Override
    public void addItemToBasket(BasketAddItemRequest request) {

    }

    @Override
    public void deleteItemFromBasket(BasketDeleteItemRequest request) {

    }

    @Override
    public void clearBasketById(int cartId) {

    }

    @Override
    public void deleteBasketById(int cartId) {

    }
}
