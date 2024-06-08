package com.turkcell.catalogservice.services.mappers;

import com.turkcell.catalogservice.entities.Product;
import com.turkcell.catalogservice.services.dtos.requests.ProductAddRequest;
import com.turkcell.catalogservice.services.dtos.requests.ProductUpdateRequest;
import com.turkcell.catalogservice.services.dtos.responses.ProductGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "catalog.id",source = "catalogId")
    Product productFromAddRequest(ProductAddRequest request);
    @Mapping(target = "catalog.id",source = "catalogId")
    Product productFromUpdateRequest(ProductUpdateRequest request);
    @Mapping(target = "catalogId", source = "catalog.id")
    ProductGetResponse getResponseFromProduct(Product product);
}
