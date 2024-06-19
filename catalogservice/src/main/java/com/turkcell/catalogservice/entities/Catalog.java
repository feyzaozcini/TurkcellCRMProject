package com.turkcell.catalogservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Catalog extends BaseEntity{

    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "catalog")
    private Set<Product> products;

}
