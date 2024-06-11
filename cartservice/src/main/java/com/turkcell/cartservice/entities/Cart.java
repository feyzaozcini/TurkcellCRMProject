package com.turkcell.cartservice.entities;

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

public class Cart extends BaseEntity {
    @Column(name = "account_id")
    private int accountId;
    @Column(name = "total_amount")
    private double totalAmount;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> items;
}
