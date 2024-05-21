package com.turkcell.authserver.clients;

import com.turkcell.authserver.core.configurations.FeignConfig;
import com.turkcell.authserver.services.dtos.responses.CustomerGet;
import feign.Headers;
import feign.Param;
import io.swagger.v3.oas.annotations.headers.Header;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "customerservice", url = "http://localhost:8091", configuration = FeignConfig.class)
public interface CustomerServiceClient {
    @GetMapping(value = "/api/v1/customer/all")
    List<CustomerGet> getCustomers();
}
