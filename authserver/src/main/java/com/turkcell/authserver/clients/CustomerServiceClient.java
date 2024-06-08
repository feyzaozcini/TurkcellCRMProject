package com.turkcell.authserver.clients;
import com.turkcell.authserver.core.configurations.FeignConfig;
import com.turkcell.authserver.services.dtos.requests.CustomerUpdateRequest;
import com.turkcell.authserver.services.dtos.requests.SearchRequest;
import com.turkcell.authserver.services.dtos.responses.CustomerAddressGet;
import com.turkcell.authserver.services.dtos.responses.CustomerContactGet;
import com.turkcell.authserver.services.dtos.responses.CustomerGet;
import com.turkcell.authserver.services.dtos.responses.SearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "customerservice", url = "http://localhost:8084", configuration = FeignConfig.class)
public interface CustomerServiceClient {
    @GetMapping(value = "/api/v1/customer/all")
    List<CustomerGet> getCustomers();

    @GetMapping(value = "/api/v1/customer/addresses")
    List<CustomerAddressGet> getCustomerAddressesByCustomerId(@RequestParam int customerId);

    @GetMapping(value = "/api/v1/customer/contacts")
    List<CustomerContactGet> getCustomerContactsByCustomerId(@RequestParam int customerId);

    @PostMapping(value = "/api/v1/customer/search")
    List<SearchResponse> searchCustomer(@RequestBody SearchRequest request);

    @PutMapping(value = "/api/v1/customer/update")
    void updateCustomer(@RequestBody CustomerUpdateRequest request);
}
