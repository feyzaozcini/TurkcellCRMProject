package com.turkcell.customerservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
public class Address extends BaseEntity {

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "street")
    private String street;

    @Column(name = "address_description")
    private String addressDescription;

    @Column(name = "house_flat_number_street")
    private String houseFlatNumberStreet;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + getId() +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", addressDescription='" + addressDescription + '\'' +
                ", houseFlatNumberStreet='" + houseFlatNumberStreet + '\'' +
                '}';
    }
}
