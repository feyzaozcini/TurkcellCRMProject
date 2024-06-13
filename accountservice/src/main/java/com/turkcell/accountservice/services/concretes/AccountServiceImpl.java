package com.turkcell.accountservice.services.concretes;
import com.turkcell.accountservice.entitites.Account;
import com.turkcell.accountservice.repositories.AccountRepository;
import com.turkcell.accountservice.services.abstracts.AccountService;
import com.turkcell.accountservice.services.dtos.requests.account.AccountAddRequest;
import com.turkcell.accountservice.services.dtos.requests.account.AccountUpdateRequest;
import com.turkcell.accountservice.services.dtos.responses.account.AccountGetResponse;
import com.turkcell.accountservice.services.mappers.AccountMapper;
import com.turkcell.accountservice.services.rules.AccountBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountBusinessRules accountBusinessRules;
    public void addAccount(AccountAddRequest request){
        accountBusinessRules.checkCustomerIsExistById(request.getCustomerId());
        accountBusinessRules.checkAddressIsExistById(request.getAddressId());
        Account account = AccountMapper.INSTANCE.accountFromAddRequest(request);
        account.setCreatedDate(LocalDateTime.now());
        account.setActive(true);
        accountRepository.save(account);
    }
    public List<AccountGetResponse> getAccounts(){
        return accountRepository.findAll().stream().filter(response -> response.isActive()).map((response) -> AccountMapper.INSTANCE.getResponseFromAccount(response)).collect(Collectors.toList());
    }

    public AccountGetResponse getAccountById(int id) {
        accountBusinessRules.checkAccountExistById(id);
        return AccountMapper.INSTANCE.getResponseFromAccount(accountRepository.findById(id).orElseThrow());
    }



    public void deleteAccountById(int id) {
        accountBusinessRules.checkAccountExistById(id);
        Account account = accountRepository.findById(id).orElseThrow();
        accountBusinessRules.checkProductListIsNotEmpty(account);
        account.setActive(false);
        account.setDeletedDate(LocalDateTime.now());
        accountRepository.save(account);
    }

    public void updateAccount(AccountUpdateRequest request) {
        accountBusinessRules.checkAccountExistById(request.getId());
        Account account = accountRepository.findById(request.getId()).orElseThrow();
        AccountMapper.INSTANCE.updateAccountFromUpdateRequest(request, account);
        accountBusinessRules.checkProductIdsAreExist(account.getProductIds());
        account.setUpdatedDate(LocalDateTime.now());
        accountRepository.save(account);
    }

}
