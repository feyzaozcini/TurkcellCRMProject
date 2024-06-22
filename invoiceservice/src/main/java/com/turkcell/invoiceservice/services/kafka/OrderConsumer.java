package com.turkcell.invoiceservice.services.kafka;
import com.turkcell.common.events.OrderEvent;
import com.turkcell.invoiceservice.services.abstracts.InvoiceService;
import com.turkcell.invoiceservice.services.dtos.requests.InvoiceAddRequest;
import com.turkcell.invoiceservice.services.dtos.response.InvoiceAddResponse;
import com.turkcell.invoiceservice.services.loggers.concretes.ConsoleLogger;
import com.turkcell.invoiceservice.services.loggers.abstracts.LoggerBase;
import com.turkcell.invoiceservice.services.mappers.InvoiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class OrderConsumer {
    private final InvoiceService invoiceService;
    private final LoggerBase consoleLogger = new ConsoleLogger();
    @KafkaListener(topics = {"orderTopic"})
    public void consumeOrderTopic(OrderEvent orderEvent) {
        InvoiceAddRequest request = InvoiceMapper.INSTANCE.invoiceFromInvoiceEvent(orderEvent);
        InvoiceAddResponse response = invoiceService.addInvoice(request);
        consoleLogger.logNewInvoice(response);
    }
}
