package com.turkcell.invoiceservice.services.rules;

import com.turkcell.invoiceservice.repositories.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class InvoiceBusinessRules {
    private final InvoiceRepository invoiceRepository;

    public void isInvoiceExist(int id){
        if(!invoiceRepository.existsById(id) || !invoiceRepository.findById(id).orElseThrow().isActive())
            throw new RuntimeException("");
    }


}
