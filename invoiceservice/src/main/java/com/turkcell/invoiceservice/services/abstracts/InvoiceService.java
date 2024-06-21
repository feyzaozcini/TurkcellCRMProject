package com.turkcell.invoiceservice.services.abstracts;

import com.turkcell.common.events.InvoiceEvent;
import com.turkcell.invoiceservice.services.dtos.requests.InvoiceAddRequest;
import com.turkcell.invoiceservice.services.dtos.response.InvoiceAddResponse;
import com.turkcell.invoiceservice.services.dtos.response.InvoiceGetResponse;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

public interface InvoiceService {
    InvoiceAddResponse addInvoice(InvoiceAddRequest request);
    InvoiceGetResponse getInvoiceById(int id);
    List<InvoiceGetResponse> getInvoices();
    void deleteInvoiceById(int id);

//    @KafkaListener(topics = {"orderTopic"})
//    void consumeKafkaMessage(InvoiceEvent invoiceEvent);
}
