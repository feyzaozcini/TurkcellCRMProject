package com.turkcell.catalogservice.services.mappers;

import com.turkcell.catalogservice.entities.Product;
import com.turkcell.catalogservice.services.dtos.requests.ProductAddRequest;
import com.turkcell.catalogservice.services.dtos.requests.ProductUpdateRequest;
import com.turkcell.catalogservice.services.dtos.responses.CreatedProductResponse;
import com.turkcell.catalogservice.services.dtos.responses.ProductGetResponse;
import com.turkcell.catalogservice.services.dtos.responses.UpdatedProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.parameters.P;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    CreatedProductResponse getResponseFromCreatedProduct(Product product);
    @Mapping(target = "catalog.id",source = "catalogId")
    Product productFromAddRequest(ProductAddRequest request);
    @Mapping(target = "catalog.id",source = "catalogId")
    Product productFromUpdateRequest(ProductUpdateRequest request);
    @Mapping(target = "catalogId", source = "catalog.id")
    ProductGetResponse getResponseFromProduct(Product product);

    @Mapping(target = "catalogId", source = "catalog.id")
    UpdatedProductResponse getResponseFromUpdatedProduct(Product product);
}
