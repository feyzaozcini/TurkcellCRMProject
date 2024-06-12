package com.turkcell.customerservice.services.rules;

import com.turkcell.customerservice.core.utils.types.BusinessException;
import com.turkcell.customerservice.core.utils.types.NotFoundException;
import com.turkcell.customerservice.repositories.IndividualCustomerRepository;
import com.turkcell.customerservice.services.dtos.request.IndividualCustomerSearchRequest;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerSearchResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IndividualCustomerBusinessRules {
    private final IndividualCustomerRepository individualCustomerRepository;

    public void individualCustomerMustBeUnique(Long nationalNumber) {
        if (individualCustomerRepository.existsIndividualCustomerByNationalityId(nationalNumber)) {
            throw new BusinessException("Individual customer already exists");
        }
    }

    public void isIndividualCustomerExist(int id){
        if(!individualCustomerRepository.existsById(id) || !individualCustomerRepository.findById(id).orElseThrow().getActive()){
            throw new NotFoundException("No customer found with id: " + id);
        }
    }

    public void individualCustomerSearchCheckExist(IndividualCustomerSearchRequest request){
        List<IndividualCustomerSearchResponse> results = individualCustomerRepository.search(request);
        if (results.isEmpty())
            throw new NotFoundException("No customer found! Would you like to create the customer?");
    }
}
