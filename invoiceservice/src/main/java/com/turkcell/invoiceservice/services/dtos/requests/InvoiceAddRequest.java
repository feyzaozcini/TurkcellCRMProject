package com.turkcell.invoiceservice.services.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceAddRequest {
    private int customerId;
    private int accountId;
    private int serviceAddress;
    private Map<Integer,Integer> productIds;
    private float totalAmount;
}
