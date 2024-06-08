package com.turkcell.accountservice.services.abstracts;

import com.turkcell.accountservice.services.dtos.requests.AccountAddRequest;
import com.turkcell.accountservice.services.dtos.requests.AccountUpdateRequest;
import com.turkcell.accountservice.services.dtos.responses.AccountGetResponse;

import java.util.List;

public interface AccountService {
    void addAccount(AccountAddRequest request);
    List<AccountGetResponse> getAccounts();
    void updateAccount(AccountUpdateRequest request);
    void deleteAccountById(int id);
    AccountGetResponse getAccountById(int id);
    String getToken();
}
