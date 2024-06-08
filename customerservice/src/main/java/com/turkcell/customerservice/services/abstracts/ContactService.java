package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.services.dtos.request.ContactUpdateRequest;

public interface ContactService {
    void updateContact(ContactUpdateRequest request);
}
