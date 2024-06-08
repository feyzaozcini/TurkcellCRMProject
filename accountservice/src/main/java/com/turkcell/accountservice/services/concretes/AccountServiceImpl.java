package com.turkcell.accountservice.services.concretes;
import com.turkcell.accountservice.core.utils.types.BusinessException;
import com.turkcell.accountservice.core.utils.types.NotFoundException;
import com.turkcell.accountservice.entitites.Account;
import com.turkcell.accountservice.repositories.AccountRepository;
import com.turkcell.accountservice.services.abstracts.AccountService;
import com.turkcell.accountservice.services.dtos.requests.AccountAddRequest;
import com.turkcell.accountservice.services.dtos.requests.AccountUpdateRequest;
import com.turkcell.accountservice.services.dtos.responses.AccountGetResponse;
import com.turkcell.accountservice.services.mappers.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private String token;
    public void addAccount(AccountAddRequest request){
        Account account = AccountMapper.INSTANCE.accountFromAddRequest(request);
        account.setCreatedDate(LocalDateTime.now());
        account.setActive(true);
        accountRepository.save(account);
    }
    public List<AccountGetResponse> getAccounts(){
        return accountRepository.findAll().stream().filter(response -> response.isActive()).map((response) -> AccountMapper.INSTANCE.getResponseFromAccount(response)).collect(Collectors.toList());
    }

    public AccountGetResponse getAccountById(int id) {
        checkFound(id);
        return AccountMapper.INSTANCE.getResponseFromAccount(accountRepository.findById(id).orElseThrow());
    }

    @Override
    public String getToken() {
        return token;
    }

    public void deleteAccountById(int id) {
        checkFound(id);
        Account account = accountRepository.findById(id).orElseThrow();
        if (account.getProductIds() == null) {
            account.setActive(false);
            account.setDeletedDate(LocalDateTime.now());
            accountRepository.save(account);
        } else {
            throw new BusinessException("There are product/products connected to the billing account");
        }
    }

    public void updateAccount(AccountUpdateRequest request) {
        checkFound(request.getId());
        Account account = accountRepository.findById(request.getId()).orElseThrow();
        AccountMapper.INSTANCE.updateAccountFromUpdateRequest(request, account); //AccountMapper.INSTANCE.accountFromUpdateRequest(request);
        account.setUpdatedDate(LocalDateTime.now());
        accountRepository.save(account);
    }

    public void checkFound(int id){
        if(!accountRepository.existsById(id) || !accountRepository.findById(id).orElseThrow().isActive())
            throw new NotFoundException("Bu idye sahip account bulunamadi!");
    }
}
