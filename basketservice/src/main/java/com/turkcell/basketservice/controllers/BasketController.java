package com.turkcell.basketservice.controllers;

import com.turkcell.basketservice.services.abstracts.BasketService;
import com.turkcell.basketservice.services.dtos.requests.BasketAddItemRequest;
import com.turkcell.basketservice.services.dtos.requests.BasketCreateRequest;
import com.turkcell.basketservice.services.dtos.requests.BasketDeleteItemRequest;
import com.turkcell.basketservice.services.dtos.responses.BasketGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;
    @PostMapping("/create")
    public BasketGetResponse createBasket(@RequestBody BasketCreateRequest request){
        return basketService.createBasket(request);
    }

    @GetMapping("/{accountId}")
    public BasketGetResponse getBasketById(@PathVariable int accountId){
        return basketService.getBasket(accountId);
    }

    @PostMapping("/addItem")
    public void addItemToCart(@RequestBody BasketAddItemRequest request){
        basketService.addItemToBasket(request);
    }

    @DeleteMapping("/deleteItem")
    public void deleteItemFromCart(@RequestBody BasketDeleteItemRequest request){
        basketService.deleteItemFromBasket(request);
    }

    @DeleteMapping("/clear/{id}")
    public void clearCartById(@PathVariable int id){
        basketService.clearBasketById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCartById(@PathVariable int id){
        basketService.deleteBasketById(id);
    }
}
