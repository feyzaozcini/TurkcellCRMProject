package com.turkcell.catalogservice.services.rules;

import com.turkcell.catalogservice.core.utils.types.NotFoundException;
import com.turkcell.catalogservice.entities.Catalog;
import com.turkcell.catalogservice.repositories.CatalogRepository;
import com.turkcell.catalogservice.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductBusinessRules {
    private final CatalogRepository catalogRepository;
    private final ProductRepository productRepository;

    public Catalog findCatalogById(int catalogId) {
        return catalogRepository.findById(catalogId)
                .orElseThrow(() -> new NotFoundException("İlgili katalog bulunamadı"));
    }

    public void checkIfProductExistsById(int productId) {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException("İlgili ürün bulunamadı!");
        }
    }
}

