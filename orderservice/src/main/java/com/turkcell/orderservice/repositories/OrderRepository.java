package com.turkcell.orderservice.repositories;

import com.turkcell.orderservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer>{
    boolean existsByCustomerId(int customerId);
    boolean existsById(int id);
}
