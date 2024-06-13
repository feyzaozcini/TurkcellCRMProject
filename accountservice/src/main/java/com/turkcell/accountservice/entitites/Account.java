package com.turkcell.accountservice.entitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "account_name")
    private String accountName;
    @Column(name = "account_description")
    private String description;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "status")
    private String status;
    @ElementCollection
    @CollectionTable(name = "product_ids")
    @Column(name = "products")
    private List<Integer> productIds;
    private Integer addressId;
    private Integer customerId;
}