package com.turkcell.orderservice.clients;
import com.turkcell.orderservice.core.configurations.FeignConfiguration;
import com.turkcell.orderservice.clients.dtos.customerservice.AddressResponse;
import com.turkcell.orderservice.clients.dtos.customerservice.CustomerGetResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "customerservice", url = "http://localhost:8084", configuration = FeignConfiguration.class)
@FeignClient(name = "customer-service", url = "http://customer-service:8084", configuration = FeignConfiguration.class)
public interface CustomerServiceClient {
    @GetMapping("/api/v1/individualCustomer/{id}")
    CustomerGetResponse getCustomer(@PathVariable int id);
    @GetMapping(value = "/api/v1/address/{id}")
    AddressResponse getAddressDetails(@PathVariable int id);
}
