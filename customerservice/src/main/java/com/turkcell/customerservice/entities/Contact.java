package com.turkcell.customerservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@NoArgsConstructor
public class Contact extends BaseEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "home_phone")
    private String homePhone;

    @Column(name = "fax")
    private String fax;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
