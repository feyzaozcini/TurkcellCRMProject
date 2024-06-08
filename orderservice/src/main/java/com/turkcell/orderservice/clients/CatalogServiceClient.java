package com.turkcell.orderservice.clients;

import com.turkcell.orderservice.core.configurations.FeignConfiguration;
import com.turkcell.orderservice.services.dtos.responses.ProductGetResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalogservice", url = "http://localhost:8085", configuration = FeignConfiguration.class)

public interface CatalogServiceClient {
    @GetMapping(value = "/api/v1/product/{id}")
    ProductGetResponse getProductById(@PathVariable int id);
}
