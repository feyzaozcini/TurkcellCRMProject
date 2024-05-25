package com.turkcell.catalogservice.repositories;

import com.turkcell.catalogservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
