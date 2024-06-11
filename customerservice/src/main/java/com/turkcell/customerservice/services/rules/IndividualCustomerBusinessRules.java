package com.turkcell.customerservice.services.rules;

import com.turkcell.customerservice.core.utils.types.BusinessException;
import com.turkcell.customerservice.repositories.IndividualCustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IndividualCustomerBusinessRules {
    private final IndividualCustomerRepository individualCustomerRepository;

    public void individualCustomerMustBeUnique(Long nationalNumber) {
        if (individualCustomerRepository.existsIndividualCustomerByNationalityId(nationalNumber)) {
            throw new BusinessException("Individual customer already exists");
        }
    }
}
