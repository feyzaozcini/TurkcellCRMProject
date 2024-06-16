package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.entities.IndividualCustomer;
import com.turkcell.customerservice.repositories.IndividualCustomerRepository;
import com.turkcell.customerservice.services.abstracts.IndividualCustomerService;
import com.turkcell.customerservice.services.dtos.request.*;
import com.turkcell.customerservice.services.dtos.response.CreatedIndividualCustomerResponse;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerGetResponse;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerSearchResponse;
import com.turkcell.customerservice.services.dtos.response.UpdatedIndividualCustomerResponse;
import com.turkcell.customerservice.services.mappers.IndividualCustomerMapper;
import com.turkcell.customerservice.services.rules.IndividualCustomerBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IndividualCustomerServiceImpl implements IndividualCustomerService {
    private final IndividualCustomerRepository individualCustomerRepository;
    private final IndividualCustomerBusinessRules individualCustomerBusinessRules;
    @Override
    public CreatedIndividualCustomerResponse addCustomer(IndividualCustomerAddRequest request) {
        individualCustomerBusinessRules.individualCustomerMustBeUnique(request.getNationalityId(), 0);
        IndividualCustomer newCustomer = IndividualCustomerMapper.INSTANCE.getIndividualCustomerFromAddRequest(request);
        newCustomer.setCreatedDate(LocalDateTime.now());
        newCustomer.setActive(true);
        IndividualCustomer savedCustomer = individualCustomerRepository.save(newCustomer);
        return IndividualCustomerMapper.INSTANCE.getResponseFromCreatedIndividualCustomer(savedCustomer);
    }

    @Override
    public IndividualCustomerGetResponse getCustomerById(int id) {
        individualCustomerBusinessRules.isIndividualCustomerExist(id);
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
        individualCustomerBusinessRules.isIndividualCustomerExist(id);
        individualCustomerBusinessRules.isOrderExistRelatedIndividualCustomer(id);
        IndividualCustomer individualCustomer = individualCustomerRepository.findById(id).orElseThrow();
        individualCustomer.setActive(false);
        individualCustomer.setDeletedDate(LocalDateTime.now());
        individualCustomerRepository.save(individualCustomer);
    }

    @Override
    public UpdatedIndividualCustomerResponse updateCustomer(IndividualCustomerUpdateRequest request) {
        individualCustomerBusinessRules.individualCustomerMustBeUnique(request.getNationalityId(), request.getId());
        individualCustomerBusinessRules.isIndividualCustomerExist(request.getId());
        IndividualCustomer individualCustomer = individualCustomerRepository.findById(request.getId()).orElseThrow();
        IndividualCustomerMapper.INSTANCE.individualCustomerFromUpdateRequest(request, individualCustomer);
        individualCustomer.setUpdatedDate(LocalDateTime.now());

        IndividualCustomer savedCustomer = individualCustomerRepository.save(individualCustomer);
        UpdatedIndividualCustomerResponse response = IndividualCustomerMapper.INSTANCE.getResponseFromUpdatedIndividualCustomer(savedCustomer);
        response.setAddresses(IndividualCustomerMapper.INSTANCE.convertToAddressDtoList(savedCustomer.getAddresses()));
        return response;
    }

    @Override
    public List<IndividualCustomerSearchResponse> searchCustomer(IndividualCustomerSearchRequest request, int page, int size) {
        individualCustomerBusinessRules.individualCustomerSearchCheckExist(request);
        Pageable pageable = PageRequest.of(page, size);
        return individualCustomerRepository.search(request, pageable);
    }
}
