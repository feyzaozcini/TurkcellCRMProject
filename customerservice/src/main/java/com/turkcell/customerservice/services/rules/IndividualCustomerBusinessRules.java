package com.turkcell.customerservice.services.rules;

import com.turkcell.customerservice.clients.OrderServiceClient;
import com.turkcell.customerservice.core.utils.types.BusinessException;
import com.turkcell.customerservice.core.utils.types.NotFoundException;
import com.turkcell.customerservice.repositories.IndividualCustomerRepository;
import com.turkcell.customerservice.services.dtos.request.IndividualCustomerSearchRequest;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerSearchResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IndividualCustomerBusinessRules {
    private final IndividualCustomerRepository individualCustomerRepository;

    private final OrderServiceClient orderServiceClient;
    public void individualCustomerMustBeUnique(String nationalNumber, int customerId) {
        if (individualCustomerRepository.existsIndividualCustomerByNationalityId(nationalNumber, customerId)) {
            throw new BusinessException("Individual customer already exists");
        }
    }

    public void isIndividualCustomerExist(int id){
        if(!individualCustomerRepository.existsById(id) || !individualCustomerRepository.findById(id).orElseThrow().getActive()){
            throw new NotFoundException("No customer found with id: " + id);
        }
    }

    public void individualCustomerSearchCheckExist(IndividualCustomerSearchRequest request){
        Pageable pageable = PageRequest.of(0, 1);
        List<IndividualCustomerSearchResponse> results = individualCustomerRepository.search(request, pageable);
        if (results.isEmpty())
            throw new NotFoundException("No customer found! Would you like to create the customer?");
    }

    public void isOrderExistRelatedIndividualCustomer(int customerId){
        if(orderServiceClient.isOrderExistByCustomerId(customerId))
            throw new BusinessException("Since the customer has active products, the customer cannot be deleted.");
    }
}
