package com.turkcell.catalogservice.repositories;

import com.turkcell.catalogservice.entities.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
}
