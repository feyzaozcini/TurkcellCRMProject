package com.turkcell.catalogservice.services.mappers;

import com.turkcell.catalogservice.entities.Catalog;
import com.turkcell.catalogservice.services.dtos.responses.CatalogGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CatalogMapper {
    CatalogMapper INSTANCE = Mappers.getMapper(CatalogMapper.class);
    CatalogGetResponse getResponseFromCatalog(Catalog catalog);
}
