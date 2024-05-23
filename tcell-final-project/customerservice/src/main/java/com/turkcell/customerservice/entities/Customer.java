package com.turkcell.customerservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "nationality_id")
    private Long nationalityId;
    @Column(name = "account_number")
    private Long accountNumber;
    @Column(name = "gsm_number")
    private Long gsmNumber;
    @Column(name = "order_number")
    private Long orderNumber;
    @Column(name = "mother_name")
    private String motherName;
    @Column(name = "father_name")
    private String fatherName;
    @Column(name = "gender")
    //@Enumerated(EnumType.STRING)
    //private Gender gender;

    private String gender;
    @Column(name = "birth_date")
    private LocalDateTime birthDate;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Address> addresses;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Contact> contacts;

}
