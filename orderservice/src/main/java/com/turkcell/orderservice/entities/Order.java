package com.turkcell.orderservice.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@Entity(name = "orderr")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order extends BaseEntity{

    @Column(name = "service_address")
    private int serviceAddress;
    @Column(name = "total_amount")
    private float totalAmount;
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "account_id")
    private int accountId;
    @ElementCollection
    @Column(name = "product_quantity_ids")
    private Map<Integer, Integer> productIds;
}
