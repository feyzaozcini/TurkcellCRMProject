package com.turkcell.invoiceservice.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;
    @Column(name = "active")
    private boolean active;

    public static class Listener{
        @PrePersist
        void onPrePersist(BaseEntity baseEntity){
            baseEntity.setCreatedDate(LocalDateTime.now());
            baseEntity.setActive(true);
        }
        @PreUpdate
        void onPreUpdate(BaseEntity baseEntity){
            baseEntity.setUpdatedDate(LocalDateTime.now());
        }
        @PreRemove
        void onPreDelete(BaseEntity baseEntity){
            baseEntity.setDeletedDate(LocalDateTime.now());
            baseEntity.setActive(false);
        }
    }
}
