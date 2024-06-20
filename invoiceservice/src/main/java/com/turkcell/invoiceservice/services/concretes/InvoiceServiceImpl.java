package com.turkcell.invoiceservice.services.concretes;

import com.turkcell.common.events.InvoiceEvent;
import com.turkcell.invoiceservice.entities.BaseEntity;
import com.turkcell.invoiceservice.entities.Invoice;
import com.turkcell.invoiceservice.repositories.InvoiceRepository;
import com.turkcell.invoiceservice.services.abstracts.InvoiceService;
import com.turkcell.invoiceservice.services.dtos.requests.InvoiceAddRequest;
import com.turkcell.invoiceservice.services.dtos.response.InvoiceAddResponse;
import com.turkcell.invoiceservice.services.dtos.response.InvoiceGetResponse;
import com.turkcell.invoiceservice.services.mappers.InvoiceMapper;
import com.turkcell.invoiceservice.services.rules.InvoiceBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceBusinessRules invoiceBusinessRules;

    public InvoiceAddResponse addInvoice(InvoiceAddRequest request){
        Invoice invoice = InvoiceMapper.INSTANCE.invoiceFromAddRequest(request);
        invoice.setActive(true);
        invoice.setCreatedDate(LocalDateTime.now());
        invoiceRepository.save(invoice);
        return InvoiceMapper.INSTANCE.addResponseFromInvoice(invoice);
    }

    @KafkaListener(topics = {"orderTopic"})
    public void consumeKafkaMessage(InvoiceEvent invoiceEvent){
        System.out.println("Invoice Eventten gelen customer id: " + invoiceEvent.getCustomerId());
        Invoice invoice = new Invoice();
        invoice.setProductIds(invoiceEvent.getProductIds());
        invoice.setCustomerId(invoiceEvent.getCustomerId());
        invoice.setServiceAddress(invoiceEvent.getServiceAddress());
        invoice.setAccountId(invoiceEvent.getAccountId());
        invoice.setCreatedDate(LocalDateTime.now());
        invoice.setActive(true);
        invoiceRepository.save(invoice);
    }
    public InvoiceGetResponse getInvoiceById(int id){
        invoiceBusinessRules.isInvoiceExist(id);
        Invoice invoice = invoiceRepository.findById(id).orElseThrow();
        return InvoiceMapper.INSTANCE.getResponseFromInvoice(invoice);
    }

    public List<InvoiceGetResponse> getInvoices(){
        return invoiceRepository.findAll().stream().filter(BaseEntity::isActive).map((invoice -> InvoiceMapper.INSTANCE.getResponseFromInvoice(invoice))).collect(Collectors.toList());
    }
    public void deleteInvoiceById(int id){
        invoiceBusinessRules.isInvoiceExist(id);
        Invoice invoice = invoiceRepository.findById(id).orElseThrow();
        invoice.setActive(false);
        invoice.setDeletedDate(LocalDateTime.now());
        invoiceRepository.save(invoice);
    }

}
