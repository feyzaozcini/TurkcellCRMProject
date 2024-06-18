package com.turkcell.basketservice.services.rules;

import com.turkcell.basketservice.clients.AccountServiceClient;
import com.turkcell.basketservice.clients.CatalogServiceClient;
import com.turkcell.basketservice.entitites.Basket;
import com.turkcell.basketservice.repositories.BasketRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketBusinessRules {
    private final BasketRepository basketRepository;
    private final CatalogServiceClient catalogServiceClient;
    private final AccountServiceClient accountServiceClient;
    public void checkBasketIsNotExistByAccountId(int accountId){
//        if(!basketRepository.existsByAccountId(accountId))
//            throw new RuntimeException("Cart not found!");
//
//        if(basketRepository.existsByAccountId(accountId)){
//            for (Basket basket : basketRepository.findByAccountId(accountId)){
//                if(!basket.getActive())
//                    throw new RuntimeException("Cart not found!");
//            }
//        }

        if(basketRepository.findByAccountIdAndActive(accountId, true) == null)
            throw new RuntimeException("");
    }
    public void checkBasketIsExistByAccountId(int accountId){
//        if(basketRepository.existsByAccountId(accountId)){
//            for (Basket basket : basketRepository.findByAccountId(accountId)){
//                if(basket.getActive())
//                    throw new RuntimeException("Cart already found!");
//            }
//        }

        if(basketRepository.findByAccountIdAndActive(accountId, true) != null)
            throw new RuntimeException("");
    }


    public void isProductExist(int productId){
        try{
            catalogServiceClient.getProductById(productId);
        }
        catch (FeignException.NotFound exception){
            throw new RuntimeException(" ");
        }
    }
    public void isAccountExist(int accountId){
        try {
            accountServiceClient.getAccountById(accountId);
        }
        catch (FeignException.NotFound exception){
            throw new RuntimeException("");
        }
    }
}
