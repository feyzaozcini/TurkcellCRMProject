package com.turkcell.invoiceservice.repositories;

import com.turkcell.invoiceservice.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    boolean existsById(int id);
}
