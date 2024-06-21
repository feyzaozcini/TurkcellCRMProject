package com.turkcell.invoiceservice.entities;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice extends BaseEntity{
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "account_id")
    private int accountId;
    @Column(name = "service_address")
    private int serviceAddress;
    @Column(name = "total_amount")
    private float totalAmount;
    @Column(name = "products")
    @ElementCollection
    private Map<Integer, Integer> productIds;
}
