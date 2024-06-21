package com.turkcell.basketservice.clients;

import com.turkcell.basketservice.clients.dtos.accountservice.AccountGetResponse;
import com.turkcell.basketservice.core.configurations.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "accountservice", url = "http://localhost:8087", configuration = FeignConfiguration.class)
@FeignClient(name = "account-service", url = "http://account-service:8087", configuration = FeignConfiguration.class)
public interface AccountServiceClient {
    @GetMapping("/api/v1/account/{id}")
    AccountGetResponse getAccountById(@PathVariable int id);
}