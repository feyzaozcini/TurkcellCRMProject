package com.turkcell.accountservice.controllers;

import com.turkcell.accountservice.services.abstracts.AccountService;
import com.turkcell.accountservice.services.dtos.requests.account.AccountAddRequest;
import com.turkcell.accountservice.services.dtos.requests.account.AccountUpdateRequest;
import com.turkcell.accountservice.services.dtos.responses.account.AccountAddResponse;
import com.turkcell.accountservice.services.dtos.responses.account.AccountGetResponse;
import com.turkcell.accountservice.services.dtos.responses.account.AccountUpdateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/add")
    public AccountAddResponse addAccount(@RequestBody @Valid AccountAddRequest request) {
        return accountService.addAccount(request);
    }

    @GetMapping("/all")
    public List<AccountGetResponse> getAccounts() {
        return accountService.getAccounts();
    }

    @PutMapping("/update")
    public AccountUpdateResponse updateAccount(@RequestBody AccountUpdateRequest request) {
        return accountService.updateAccount(request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAccountById(@PathVariable int id) {
        accountService.deleteAccountById(id);
    }

    @GetMapping("/{id}")
    public AccountGetResponse getAccountById(@PathVariable int id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/getCustomerAccounts/{customerId}")
    public List<AccountGetResponse> getAccountsByCustomerId(@PathVariable int customerId) {
        return accountService.getAccountsByCustomerId(customerId);
    }
}
