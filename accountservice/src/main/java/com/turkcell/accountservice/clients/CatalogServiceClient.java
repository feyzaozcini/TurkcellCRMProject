package com.turkcell.accountservice.clients;

import com.turkcell.accountservice.clients.dtos.catalogservice.ProductGetDto;
import com.turkcell.accountservice.core.configurations.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "catalogservice", url = "http://localhost:8085", configuration = FeignConfiguration.class) //for local
@FeignClient(name = "catalog-service", url = "http://catalog-service:8085", configuration = FeignConfiguration.class)
public interface CatalogServiceClient {
    @GetMapping("/api/v1/product/{id}")
    ProductGetDto getProductById(@PathVariable int id);
}
