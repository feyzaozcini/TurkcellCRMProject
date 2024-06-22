package com.turkcell.accountservice.clients;

import com.turkcell.accountservice.core.configurations.FeignConfiguration;
import com.turkcell.accountservice.clients.dtos.customerservice.AddressGetResponse;
import com.turkcell.accountservice.clients.dtos.customerservice.IndividualCustomerGetResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "customerservice", url = "http://localhost:8084", configuration = FeignConfiguration.class) //for local
@FeignClient(name = "customer-service", url = "http://customer-service:8084", configuration = FeignConfiguration.class)
public interface CustomerServiceClient {
    @GetMapping("/api/v1/individualCustomer/{id}")
    IndividualCustomerGetResponse getCustomerById(@PathVariable int id);
    @GetMapping("/api/v1/address/{id}")
    AddressGetResponse getAddressById(@PathVariable int id);
}
