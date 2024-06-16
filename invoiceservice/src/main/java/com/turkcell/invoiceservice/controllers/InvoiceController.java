package com.turkcell.invoiceservice.controllers;

import com.turkcell.invoiceservice.services.abstracts.InvoiceService;
import com.turkcell.invoiceservice.services.dtos.requests.InvoiceAddRequest;
import com.turkcell.invoiceservice.services.dtos.response.InvoiceAddResponse;
import com.turkcell.invoiceservice.services.dtos.response.InvoiceGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;
    @GetMapping("/{id}")
    public InvoiceGetResponse getInvoiceById(@PathVariable int id){
        return invoiceService.getInvoiceById(id);
    }

    @GetMapping("/all")
    public List<InvoiceGetResponse> getInvoices(){
        return invoiceService.getInvoices();
    }

    @PostMapping("/add")
    public InvoiceAddResponse addInvoice(@RequestBody InvoiceAddRequest request){
        return invoiceService.addInvoice(request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteInvoiceById(@PathVariable int id){
        invoiceService.deleteInvoiceById(id);
    }
}
