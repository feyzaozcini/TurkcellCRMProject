package com.turkcell.invoiceservice.services.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceAddResponse {
    private int id;
    private int customerId;
    private int accountId;
    private int serviceAddress;
    private List<Integer> productIds;
    private float totalAmount;
}
