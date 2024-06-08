package com.turkcell.customerservice.repositories;

import com.turkcell.customerservice.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    boolean existsById(int id);
}
