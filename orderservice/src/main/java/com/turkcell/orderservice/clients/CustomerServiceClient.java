package com.turkcell.orderservice.clients;
import com.turkcell.orderservice.core.configurations.FeignConfiguration;
import com.turkcell.orderservice.services.dtos.responses.AddressResponse;
import com.turkcell.orderservice.services.dtos.responses.CustomerGetResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "customerservice", url = "http://localhost:8084", configuration = FeignConfiguration.class)
public interface CustomerServiceClient {
//    @GetMapping(value = "/api/v1/customer/getDefaultAddress/{id}")
//    int getDefaultCustomerAddress(@PathVariable int id);

    @GetMapping("/api/v1/customer/{id}")
    CustomerGetResponse getCustomer(@PathVariable int id);
    @GetMapping(value = "/api/v1/address/{id}")
    AddressResponse getAddressDetails(@PathVariable int id);
}
