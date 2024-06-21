package com.turkcell.customerservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.JOINED)

public class Customer extends BaseEntity {
    @SequenceGenerator(
            name = "base_sequence_generator",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )

    @OneToMany(mappedBy = "customer")
    private List<Contact> contacts;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

}