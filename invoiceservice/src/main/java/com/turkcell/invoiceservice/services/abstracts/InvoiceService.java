package com.turkcell.invoiceservice.services.abstracts;

import com.turkcell.common.events.InvoiceEvent;
import com.turkcell.invoiceservice.services.dtos.requests.InvoiceAddRequest;
import com.turkcell.invoiceservice.services.dtos.response.InvoiceAddResponse;
import com.turkcell.invoiceservice.services.dtos.response.InvoiceGetResponse;

import java.util.List;

public interface InvoiceService {
    InvoiceAddResponse addInvoice(InvoiceAddRequest request);
    InvoiceGetResponse getInvoiceById(int id);
    List<InvoiceGetResponse> getInvoices();
    void deleteInvoiceById(int id);
    void consumeKafkaMessage(InvoiceEvent invoiceEvent);
}
