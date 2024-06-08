package com.turkcell.customerservice.repositories;

import com.turkcell.customerservice.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    boolean existsById(int id);
}
