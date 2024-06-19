package com.turkcell.accountservice.services.rules;

import com.turkcell.accountservice.clients.CustomerServiceClient;
import com.turkcell.accountservice.clients.CatalogServiceClient;
import com.turkcell.accountservice.core.utils.types.BusinessException;
import com.turkcell.accountservice.core.utils.types.NotFoundException;
import com.turkcell.accountservice.entitites.Account;
import com.turkcell.accountservice.repositories.AccountRepository;
import com.turkcell.accountservice.services.dtos.responses.account.AccountGetResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountBusinessRules {
    private final AccountRepository accountRepository;
    private final CatalogServiceClient catalogServiceClient;
    private final CustomerServiceClient customerServiceClient;

    public void checkAccountExistById(int id) {
        if (!accountRepository.existsById(id) || !accountRepository.findById(id).orElseThrow().isActive())
            throw new NotFoundException("Bu idye sahip account bulunamadi!");
    }

    public void checkProductListIsNotEmpty(Account account) {
        if (account.getProductIds() == null)
            throw new BusinessException("There are product/products connected to the billing account");
    }

    public void checkProductIdsAreExist(List<Integer> productIds) {
        for (int productId : productIds) {
            try {
                catalogServiceClient.getProductById(productId);
            } catch (FeignException.NotFound exception) {
                throw new NotFoundException(productId + " idye sahip urun bulunamadi!");
            }
        }
    }

    public void checkCustomerIsExistById(int id) {
        try {
            customerServiceClient.getCustomerById(id);
        } catch (FeignException.NotFound exception) {
            throw new NotFoundException("Customer not found!");
        }
    }

    public void checkAddressIsExistById(int id) {
        try {
            customerServiceClient.getAddressById(id);
        } catch (FeignException.NotFound exception) {
            throw new NotFoundException("Address not found!");
        }
    }

    public void checkCustomerAccountsExist(List<AccountGetResponse> accounts) {
        if(accounts == null || accounts.isEmpty()){
            throw new NotFoundException("You do not have a billing account, would you like to add it?");
        }
    }
}
