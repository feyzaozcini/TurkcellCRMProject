package com.turkcell.cartservice.controllers;

import com.turkcell.cartservice.services.abstracts.CartService;
import com.turkcell.cartservice.services.dtos.requests.CartAddItemRequest;
import com.turkcell.cartservice.services.dtos.requests.CartCreateRequest;
import com.turkcell.cartservice.services.dtos.requests.CartDeleteItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @PostMapping("/create")
    public void createCart(@RequestBody CartCreateRequest request){
        cartService.createCart(request);
    }

    @PostMapping("/addItem")
    public void addItemToCart(@RequestBody CartAddItemRequest request){
        cartService.addItemToCart(request);
    }

    @DeleteMapping("/deleteItem")
    public void deleteItemFromCart(@RequestBody CartDeleteItemRequest request){
        cartService.deleteItemFromCart(request);
    }

    @DeleteMapping("/clear/{id}")
    public void clearCartById(@PathVariable int id){
        cartService.clearCartById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCartById(@PathVariable int id){
        cartService.deleteCartById(id);
    }

}
