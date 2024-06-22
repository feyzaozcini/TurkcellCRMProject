package com.turkcell.catalogservice.services.rules;

import com.turkcell.catalogservice.core.utils.types.NotFoundException;
import com.turkcell.catalogservice.entities.BaseEntity;
import com.turkcell.catalogservice.entities.Catalog;
import com.turkcell.catalogservice.entities.Product;
import com.turkcell.catalogservice.repositories.CatalogRepository;
import com.turkcell.catalogservice.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductBusinessRules {
    private final CatalogRepository catalogRepository;
    private final ProductRepository productRepository;

    public Catalog findCatalogById(int catalogId) {
        return catalogRepository.findById(catalogId).filter(BaseEntity::getActive)
                .orElseThrow(() -> new NotFoundException("İlgili katalog bulunamadı"));
    }

    public void checkIfProductExistsById(int productId) {
        if (!productRepository.existsById(productId) || !productRepository.findById(productId).orElseThrow().getActive()) {
            throw new NotFoundException("İlgili ürün bulunamadı!");
        }
    }

    public void checkIfAnyProductIsExist(){
        if(productRepository.findAll().stream().filter(BaseEntity::getActive).collect(Collectors.toList()).isEmpty())
            throw new NotFoundException("Hicbir urun bulunamadi!");
    }
}

