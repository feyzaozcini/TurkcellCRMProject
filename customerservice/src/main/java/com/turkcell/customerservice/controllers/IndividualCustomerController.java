package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.services.abstracts.IndividualCustomerService;
import com.turkcell.customerservice.services.dtos.request.*;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerGetResponse;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/individualCustomer")
@RequiredArgsConstructor
public class IndividualCustomerController {
    private final IndividualCustomerService customerService;

    @GetMapping("/all")
    public List<IndividualCustomerGetResponse> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public IndividualCustomerGetResponse getCustomer(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/add")
    public void addCustomer(@RequestBody IndividualCustomerAddRequest individualCustomerAddRequest) {
        customerService.addCustomer(individualCustomerAddRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomerById(@PathVariable int id) {
        customerService.deleteCustomerById(id);
    }

    @PutMapping("/update")
    public void updateCustomer(@RequestBody IndividualCustomerUpdateRequest request) {
        customerService.updateCustomer(request);
    }

    @PostMapping("/search")
    public List<IndividualCustomerSearchResponse> searchCustomer(@RequestBody IndividualCustomerSearchRequest request) {
        return customerService.searchCustomer(request);
    }

}