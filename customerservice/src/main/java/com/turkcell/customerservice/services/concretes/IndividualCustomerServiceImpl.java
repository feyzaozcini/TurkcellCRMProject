package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.core.utils.types.NotFoundException;
import com.turkcell.customerservice.entities.IndividualCustomer;
import com.turkcell.customerservice.repositories.IndividualCustomerRepository;
import com.turkcell.customerservice.services.abstracts.IndividualCustomerService;
import com.turkcell.customerservice.services.dtos.request.*;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerGetResponse;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerSearchResponse;
import com.turkcell.customerservice.services.mappers.IndividualCustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IndividualCustomerServiceImpl implements IndividualCustomerService {
    private final IndividualCustomerRepository individualCustomerRepository;

    @Override
    public void addCustomer(IndividualCustomerAddRequest request) {
        IndividualCustomer newCustomer = IndividualCustomerMapper.INSTANCE.getIndividualCustomerFromAddRequest(request);
        newCustomer.setCreatedDate(LocalDateTime.now());
        newCustomer.setActive(true);
        individualCustomerRepository.save(newCustomer);
    }

    @Override
    public IndividualCustomerGetResponse getCustomerById(int id) {
        isExist(id);
        return IndividualCustomerMapper.INSTANCE.getResponseFromIndividualCustomer(individualCustomerRepository.findById(id).orElseThrow());
    }

    @Override
    public List<IndividualCustomerGetResponse> getCustomers() {
        return individualCustomerRepository.findAll().stream()
                .filter(IndividualCustomer::getActive)
                .map(IndividualCustomerMapper.INSTANCE::getResponseFromIndividualCustomer)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCustomerById(int id) {
        isExist(id);
        IndividualCustomer individualCustomer = individualCustomerRepository.findById(id).orElseThrow();
        individualCustomer.setActive(false);
        individualCustomer.setDeletedDate(LocalDateTime.now());
        individualCustomerRepository.save(individualCustomer);
    }

    @Override
    public void updateCustomer(IndividualCustomerUpdateRequest request) {
        isExist(request.getId());
        IndividualCustomer individualCustomer = individualCustomerRepository.findById(request.getId()).orElseThrow();
        IndividualCustomerMapper.INSTANCE.individualCustomerFromUpdateRequest(request, individualCustomer);
        individualCustomer.setUpdatedDate(LocalDateTime.now());

        individualCustomerRepository.save(individualCustomer);
    }

    @Override
    public List<IndividualCustomerSearchResponse> searchCustomer(IndividualCustomerSearchRequest request) {
        List<IndividualCustomerSearchResponse> results = individualCustomerRepository.search(request);
        if (results.isEmpty())
            throw new NotFoundException("No customer found! Would you like to create the customer?");
        return results;
    }

    public void isExist(int id){
        if(!individualCustomerRepository.existsById(id) || !individualCustomerRepository.findById(id).orElseThrow().getActive()){
            throw new NotFoundException("No customer found with id: " + id);
        }
    }
}
