package com.turkcell.customerservice.clients;

import com.turkcell.customerservice.core.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "orderservice", url = "http://localhost:8086", configuration = FeignConfiguration.class)

public interface OrderServiceClient {
    @GetMapping("api/v1/order/isExistByCustomerId/{customerId}")
    boolean isOrderExistByCustomerId(@PathVariable int customerId);
}
