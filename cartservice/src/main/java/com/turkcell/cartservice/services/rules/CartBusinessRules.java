package com.turkcell.cartservice.services.rules;

import com.turkcell.cartservice.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartBusinessRules {
    private final CartRepository cartRepository;
    public void checkCartIsNotExistByAccountId(int accountId){
        if(!cartRepository.existsByAccountId(accountId))
            throw new RuntimeException("Cart not found!");
    }
    public void checkCartIsExistByAccountId(int accountId){
        if(cartRepository.existsByAccountId(accountId))
            throw new RuntimeException("Cart already found!");
    }
}
