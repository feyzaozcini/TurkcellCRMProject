package com.turkcell.accountservice.services.abstracts;

import com.turkcell.accountservice.entitites.Account;
import com.turkcell.accountservice.services.dtos.requests.account.AccountAddRequest;
import com.turkcell.accountservice.services.dtos.requests.account.AccountUpdateRequest;
import com.turkcell.accountservice.services.dtos.responses.account.AccountAddResponse;
import com.turkcell.accountservice.services.dtos.responses.account.AccountGetResponse;
import com.turkcell.accountservice.services.dtos.responses.account.AccountUpdateResponse;

import java.util.List;

public interface AccountService {
    AccountAddResponse addAccount(AccountAddRequest request);
    List<AccountGetResponse> getAccounts();
    AccountUpdateResponse updateAccount(AccountUpdateRequest request);
    void deleteAccountById(int id);
    AccountGetResponse getAccountById(int id);
    List<AccountGetResponse> getAccountsByCustomerId(int customerId);
}
