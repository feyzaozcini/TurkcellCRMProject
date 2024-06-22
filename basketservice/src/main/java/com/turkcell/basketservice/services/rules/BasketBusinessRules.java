package com.turkcell.basketservice.services.rules;

import com.turkcell.basketservice.clients.AccountServiceClient;
import com.turkcell.basketservice.clients.CatalogServiceClient;
import com.turkcell.basketservice.core.utils.types.NotFoundException;
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
        if(basketRepository.findByAccountIdAndActive(accountId, true) == null)
            throw new NotFoundException("Basket not found related the account id.");
    }
    public void checkBasketIsExistByAccountId(int accountId){
        if(basketRepository.findByAccountIdAndActive(accountId, true) != null)
            throw new NotFoundException("Basket already found related the account id.");
    }

    public void checkBasketIsExistById(int id){
        if(basketRepository.findByIdAndActive(id, true) == null)
            throw new NotFoundException("Basket not found: " + id);
    }
    public void isProductExist(int productId){
        try{
            catalogServiceClient.getProductById(productId);
        }
        catch (FeignException.NotFound exception){
            throw new NotFoundException("Product not found: " +productId);
        }
    }
    public void isAccountExist(int accountId){
        try {
            accountServiceClient.getAccountById(accountId);
        }
        catch (FeignException.NotFound exception){
            throw new NotFoundException("Account not found: " + accountId);
        }
    }
}
