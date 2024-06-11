package com.turkcell.cartservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "base_sequence_generator" //generator name
    )
    @Column(name = "id")
    private int id;

    @Column(name = "createDate")
    private LocalDateTime createdDate;

    @Column(name = "updateDate")
    private LocalDateTime updatedDate;

    @Column(name = "deleteDate")
    private LocalDateTime deletedDate;

    @Column(name = "active")
    private Boolean active;
}
