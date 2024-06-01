package com.turkcell.customerservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
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
    @ManyToOne
    @JsonIgnore
    private Customer customer;
    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", addressDescription='" + addressDescription + '\'' +
                ", houseFlatNumberStreet='" + houseFlatNumberStreet + '\'' +
                '}';
    }

}
