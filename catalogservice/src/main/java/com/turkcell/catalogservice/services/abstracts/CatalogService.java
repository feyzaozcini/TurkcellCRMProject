package com.turkcell.catalogservice.services.abstracts;

import com.turkcell.catalogservice.entities.Catalog;
import com.turkcell.catalogservice.services.dtos.responses.CatalogGetResponse;

import java.util.List;

public interface CatalogService {
    List<CatalogGetResponse> getAllCatalogs();
    CatalogGetResponse getCatalogById(int id);
}
