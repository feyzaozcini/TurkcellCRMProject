package com.turkcell.invoiceservice.services.mappers;

import com.turkcell.common.events.OrderEvent;
import com.turkcell.invoiceservice.entities.Invoice;
import com.turkcell.invoiceservice.services.dtos.requests.InvoiceAddRequest;
import com.turkcell.invoiceservice.services.dtos.response.InvoiceAddResponse;
import com.turkcell.invoiceservice.services.dtos.response.InvoiceGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InvoiceMapper {
    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    Invoice invoiceFromAddRequest(InvoiceAddRequest request);

    InvoiceGetResponse getResponseFromInvoice(Invoice invoice);

    InvoiceAddResponse addResponseFromInvoice(Invoice invoice);

    InvoiceAddRequest invoiceFromInvoiceEvent(OrderEvent invoiceEvent);
}
