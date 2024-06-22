package com.turkcell.invoiceservice.services.loggers.abstracts;
import com.turkcell.invoiceservice.services.dtos.response.InvoiceAddResponse;

public abstract class LoggerBase implements Logger {
    public void logNewInvoice(InvoiceAddResponse response){
        String message = String.join("\n",
                "*INVOICE ADDED*",
                "-InvoiceId: " + response.getId(),
                "-CustomerId: " + response.getCustomerId(),
                "-AccountId: " + response.getAccountId(),
                "-ServiceAddressId: " + response.getServiceAddress(),
                "-TotalAmount: " + response.getTotalAmount()
        );
        log(message);
    }
}
