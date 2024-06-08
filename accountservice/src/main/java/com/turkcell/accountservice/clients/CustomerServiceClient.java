package com.turkcell.accountservice.clients;

import com.turkcell.accountservice.core.configurations.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "customerservice", url = "http://localhost:8084", configuration = FeignConfiguration.class)
public interface CustomerServiceClient {
}
