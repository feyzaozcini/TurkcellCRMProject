package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.services.abstracts.ContactService;
import com.turkcell.customerservice.services.dtos.request.ContactUpdateRequest;
import com.turkcell.customerservice.services.dtos.request.IndividualCustomerContactAdd;
import com.turkcell.customerservice.services.dtos.response.CreatedContactResponse;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerContactGet;
import com.turkcell.customerservice.services.dtos.response.UpdatedContactResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @PutMapping("/update")
    public UpdatedContactResponse updateContact(@Valid @RequestBody ContactUpdateRequest request) {
        return contactService.updateContact(request);
    }

    @PostMapping("/add")
    public CreatedContactResponse addContact(@Valid @RequestBody IndividualCustomerContactAdd request) {
        return contactService.addContactToIndividualCustomer(request);
    }

    @DeleteMapping("/deleteContact")
    public void deleteContactByContactId(@RequestParam int contactId) {
        contactService.deleteContactByContactId(contactId);
    }

    @GetMapping("/contacts")
    public List<IndividualCustomerContactGet> getIndividualCustomerContactsById(@RequestParam int customerId) {
        return contactService.getIndividualCustomerContactsByCustomerId(customerId);
    }

    @GetMapping("/{id}")
    public IndividualCustomerContactGet getContactById(@PathVariable int id) {
        return contactService.getContactById(id);
    }
}
