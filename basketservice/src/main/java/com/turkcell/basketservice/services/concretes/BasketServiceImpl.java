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
    public void createCart(BasketCreateRequest request) {

    }

    @Override
    public void addItemToCart(BasketAddItemRequest request) {

    }

    @Override
    public void deleteItemFromCart(BasketDeleteItemRequest request) {

    }

    @Override
    public void clearCartById(int cartId) {

    }

    @Override
    public void deleteCartById(int cartId) {

    }
}
