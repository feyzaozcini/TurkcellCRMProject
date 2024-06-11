package com.turkcell.cartservice.repositories;

import com.turkcell.cartservice.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByAccountId(int accountId);
    boolean existsByAccountId(int accountId);
}
