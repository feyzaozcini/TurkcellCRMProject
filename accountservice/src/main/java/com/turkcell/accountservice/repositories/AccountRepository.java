package com.turkcell.accountservice.repositories;

import com.turkcell.accountservice.entitites.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer>{
    boolean existsById(int id);

    List<Account> getAccountsByCustomerId(int customerId);
}
