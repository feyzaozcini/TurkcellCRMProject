package com.turkcell.basketservice.repositories;


import com.turkcell.basketservice.entitites.Basket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BasketRepository extends JpaRepository<Basket, Integer> {
    Basket findByAccountIdAndActive(int accountId, boolean filter);
    Basket findByIdAndActive(int basketId, boolean filter);
}