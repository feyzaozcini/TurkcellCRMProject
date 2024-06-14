package com.turkcell.customerservice.repositories;

import com.turkcell.customerservice.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    boolean existsById(int id);

    @Query("SELECT COUNT(a) FROM Address a WHERE a.customer.id = :customerId AND a.active = true")
    int countByCustomerId(@Param("customerId") int customerId);



}
