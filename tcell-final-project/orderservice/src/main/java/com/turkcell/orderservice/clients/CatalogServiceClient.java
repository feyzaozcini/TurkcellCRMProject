package com.turkcell.orderservice.clients;

import com.turkcell.orderservice.core.configurations.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "catalogservice", url = "http://localhost:8084", configuration = FeignConfiguration.class)

public interface CatalogServiceClient {
    @GetMapping(value = "/api/v1/product/getPriceById")
    float getPriceById(@RequestParam int productId);
}
