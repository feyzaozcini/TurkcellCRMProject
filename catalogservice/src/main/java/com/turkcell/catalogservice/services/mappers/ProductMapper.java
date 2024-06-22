package com.turkcell.catalogservice.services.mappers;

import com.turkcell.catalogservice.entities.Product;
import com.turkcell.catalogservice.services.dtos.requests.ProductAddRequest;
import com.turkcell.catalogservice.services.dtos.requests.ProductUpdateRequest;
import com.turkcell.catalogservice.services.dtos.responses.CreatedProductResponse;
import com.turkcell.catalogservice.services.dtos.responses.ProductGetResponse;
import com.turkcell.catalogservice.services.dtos.responses.UpdatedProductResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    @Mapping(target = "catalog.id",source = "catalogId")
    Product productFromAddRequest(ProductAddRequest request);
    @Mapping(target = "catalogId", source = "catalog.id")
    CreatedProductResponse getResponseFromCreatedProduct(Product product);
    @Mapping(target = "catalogId", source = "catalog.id")
    ProductGetResponse getResponseFromProduct(Product product);

    @Mapping(target = "catalogId", source = "catalog.id")
    UpdatedProductResponse getResponseFromUpdatedProduct(Product product);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void productFromUpdateRequest(ProductUpdateRequest request, @MappingTarget Product product);
}
