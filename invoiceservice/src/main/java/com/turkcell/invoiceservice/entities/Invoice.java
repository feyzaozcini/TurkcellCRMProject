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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice extends BaseEntity{
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "account_id")
    private int accountId;
    @ElementCollection
    @CollectionTable(name = "product_ids")
    @Column(name = "products")
    private List<Integer> productIds;
    @Column(name = "total_amount")
    private double totalAmount;
}
