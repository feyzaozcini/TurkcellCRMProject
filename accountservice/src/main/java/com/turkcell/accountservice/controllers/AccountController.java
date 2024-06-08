package com.turkcell.accountservice.controllers;

import com.turkcell.accountservice.services.abstracts.AccountService;
import com.turkcell.accountservice.services.dtos.requests.AccountAddRequest;
import com.turkcell.accountservice.services.dtos.requests.AccountUpdateRequest;
import com.turkcell.accountservice.services.dtos.responses.AccountGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @PostMapping("/add")
    public void addAccount(@RequestBody AccountAddRequest request){
        accountService.addAccount(request);
    }
    @GetMapping("/all")
    public List<AccountGetResponse> getAccounts(){
        return accountService.getAccounts();
    }

    @PutMapping("/update")
    public void updateAccount(@RequestBody AccountUpdateRequest request){
        accountService.updateAccount(request);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteAccountById(@PathVariable int id){
        accountService.deleteAccountById(id);
    }

    @GetMapping("/{id}")
    public AccountGetResponse getAccountById(@PathVariable int id){
        return accountService.getAccountById(id);
    }
}
