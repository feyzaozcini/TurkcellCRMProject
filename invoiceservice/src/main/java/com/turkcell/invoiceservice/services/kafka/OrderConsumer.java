package com.turkcell.invoiceservice.services.kafka;

import com.turkcell.common.events.InvoiceEvent;
import com.turkcell.invoiceservice.entities.Invoice;
import com.turkcell.invoiceservice.repositories.InvoiceRepository;
import com.turkcell.invoiceservice.services.mappers.InvoiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderConsumer {
    private final InvoiceRepository invoiceRepository;
    @KafkaListener(topics = {"orderTopic"})
    public void consumeKafkaMessage(InvoiceEvent invoiceEvent) {
        Invoice invoice = InvoiceMapper.INSTANCE.invoiceFromInvoiceEvent(invoiceEvent);
        invoice.setCreatedDate(LocalDateTime.now());
        invoice.setActive(true);
        invoiceRepository.save(invoice);
        String[] lines = {"**ORDER RECEIVED**", "-CustomerId: " + invoiceEvent.getCustomerId(), "-AccountId: " + invoiceEvent.getAccountId(), "-ServiceAddressId: " + invoiceEvent.getServiceAddress(), "-TotalAmount: " + invoiceEvent.getTotalAmount()};
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
