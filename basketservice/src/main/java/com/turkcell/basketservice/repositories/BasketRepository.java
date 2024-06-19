package com.turkcell.basketservice.repositories;


import com.turkcell.basketservice.entitites.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Integer> {
    List<Basket> findByAccountId(int accountId);
    Basket findByAccountIdAndActive(int accountId, boolean filter);
    boolean existsByAccountId(int accountId);

    Basket findByIdAndActive(int basketId, boolean filter);
}