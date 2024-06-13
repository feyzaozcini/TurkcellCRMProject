package com.turkcell.orderservice.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Entity(name = "order")
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
    @ElementCollection
    @Column(name = "product_ids")
    private Set<Integer> productIds;
}
