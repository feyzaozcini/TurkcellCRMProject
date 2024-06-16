package com.turkcell.basketservice.entitites;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Basket extends BaseEntity {
    @Column(name = "account_id")
    private int accountId;
    @Column(name = "total_amount")
    private double totalAmount;
    @OneToMany(cascade = CascadeType.ALL)
    private List<BasketItem> items;
}