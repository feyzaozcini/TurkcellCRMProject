package com.turkcell.accountservice.services.abstracts;

import com.turkcell.accountservice.services.dtos.requests.account.AccountAddRequest;
import com.turkcell.accountservice.services.dtos.requests.account.AccountUpdateRequest;
import com.turkcell.accountservice.services.dtos.responses.account.AccountGetResponse;

import java.util.List;

public interface AccountService {
    void addAccount(AccountAddRequest request);
    List<AccountGetResponse> getAccounts();
    void updateAccount(AccountUpdateRequest request);
    void deleteAccountById(int id);
    AccountGetResponse getAccountById(int id);
}
