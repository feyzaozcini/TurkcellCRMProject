package com.turkcell.customerservice.service.concretes;

import com.turkcell.customerservice.clients.OrderServiceClient;
import com.turkcell.customerservice.core.utils.types.BusinessException;
import com.turkcell.customerservice.core.utils.types.NotFoundException;
import com.turkcell.customerservice.entities.IndividualCustomer;
import com.turkcell.customerservice.entities.enums.Gender;
import com.turkcell.customerservice.repositories.IndividualCustomerRepository;
import com.turkcell.customerservice.services.concretes.IndividualCustomerServiceImpl;
import com.turkcell.customerservice.services.dtos.request.IndividualCustomerAddRequest;
import com.turkcell.customerservice.services.dtos.request.IndividualCustomerSearchRequest;
import com.turkcell.customerservice.services.dtos.request.IndividualCustomerUpdateRequest;
import com.turkcell.customerservice.services.dtos.response.CreatedIndividualCustomerResponse;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerGetResponse;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerSearchResponse;
import com.turkcell.customerservice.services.dtos.response.UpdatedIndividualCustomerResponse;
import com.turkcell.customerservice.services.mappers.IndividualCustomerMapper;
import com.turkcell.customerservice.services.rules.IndividualCustomerBusinessRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class IndividualCustomerServiceImplTest {

    @Mock
    private IndividualCustomerRepository individualCustomerRepository;

    @Mock
    private IndividualCustomerBusinessRules individualCustomerBusinessRules;

    @Mock
    private OrderServiceClient orderServiceClient;

    @InjectMocks
    private IndividualCustomerServiceImpl individualCustomerService;

    private IndividualCustomerAddRequest request;
    private IndividualCustomer newCustomer;

    @BeforeEach
    void setUp() {
        request = new IndividualCustomerAddRequest();
        request.setFirstName(generateRandomString(2, 50));
        request.setSecondName(generateRandomString(2, 50));
        request.setLastName(generateRandomString(2, 50));
        request.setNationalityId(generateRandomNumericString(11, 11));
        request.setAccountNumber(generateRandomNumericString(10, 15));
        request.setGsmNumber(generateRandomNumericString(10, 15));
        request.setOrderNumber(generateRandomNumericString(10, 15));
        request.setMotherName(generateRandomString(2, 50));
        request.setFatherName(generateRandomString(2, 50));
        request.setGender(Gender.MALE);
        request.setBirthDate(LocalDate.of(1990, 10, 1));

        newCustomer = IndividualCustomerMapper.INSTANCE.getIndividualCustomerFromAddRequest(request);
        newCustomer.setCreatedDate(LocalDateTime.now());
        newCustomer.setActive(true);
    }

    @Test
    void testAddCustomer() {
        when(individualCustomerRepository.save(any(IndividualCustomer.class))).thenReturn(newCustomer);
        CreatedIndividualCustomerResponse response = individualCustomerService.addCustomer(request);

        assertNotNull(response);
        assertEquals(newCustomer.getId(), response.getId());
        assertEquals(Gender.MALE, response.getGender());
        assertEquals(LocalDate.of(1990, 10, 1), response.getBirthDate());

        verify(individualCustomerRepository).save(any(IndividualCustomer.class));
    }

    @Test
    void testGetCustomerById() {
        when(individualCustomerRepository.findById(newCustomer.getId())).thenReturn(Optional.of(newCustomer));

        IndividualCustomerGetResponse response = individualCustomerService.getCustomerById(newCustomer.getId());

        assertNotNull(response);
        assertEquals(newCustomer.getId(), response.getId());

        verify(individualCustomerBusinessRules).isIndividualCustomerExist(newCustomer.getId());
        verify(individualCustomerRepository).findById(newCustomer.getId());
    }

    @Test
    void testGetCustomers() {
        when(individualCustomerRepository.findAll()).thenReturn(Collections.singletonList(newCustomer));

        List<IndividualCustomerGetResponse> responses = individualCustomerService.getCustomers();

        assertNotNull(responses);
        assertFalse(responses.isEmpty());
        assertEquals(newCustomer.getId(), responses.get(0).getId());

        verify(individualCustomerRepository).findAll();
    }

    @Test
    void testDeleteCustomerById() {
        when(individualCustomerRepository.findById(newCustomer.getId())).thenReturn(Optional.of(newCustomer));

        individualCustomerService.deleteCustomerById(newCustomer.getId());

        assertFalse(newCustomer.getActive());
        assertNotNull(newCustomer.getDeletedDate());

        verify(individualCustomerBusinessRules).isIndividualCustomerExist(newCustomer.getId());
        verify(individualCustomerBusinessRules).isOrderExistRelatedIndividualCustomer(newCustomer.getId());
        verify(individualCustomerRepository).findById(newCustomer.getId());
        verify(individualCustomerRepository).save(newCustomer);
    }

    @Test
    void testUpdateCustomer() {
        IndividualCustomerUpdateRequest updateRequest = new IndividualCustomerUpdateRequest();
        updateRequest.setId(newCustomer.getId());
        updateRequest.setNationalityId(newCustomer.getNationalityId());
        updateRequest.setFirstName(generateRandomString(2, 50));
        updateRequest.setLastName(generateRandomString(2, 50));
        updateRequest.setBirthDate(LocalDate.of(1990, 10, 1));

        when(individualCustomerRepository.findById(newCustomer.getId())).thenReturn(Optional.of(newCustomer));
        when(individualCustomerRepository.save(any(IndividualCustomer.class))).thenReturn(newCustomer);

        UpdatedIndividualCustomerResponse response = individualCustomerService.updateCustomer(updateRequest);

        assertNotNull(response);
        assertEquals(newCustomer.getId(), response.getId());

        verify(individualCustomerBusinessRules).individualCustomerMustBeUnique(updateRequest.getNationalityId(), updateRequest.getId());
        verify(individualCustomerBusinessRules).isIndividualCustomerExist(updateRequest.getId());
        verify(individualCustomerRepository).findById(updateRequest.getId());
        verify(individualCustomerRepository).save(any(IndividualCustomer.class));
    }

    @Test
    void testSearchCustomer() {
        IndividualCustomerSearchRequest searchRequest = new IndividualCustomerSearchRequest();
        Pageable pageable = PageRequest.of(0, 10);

        when(individualCustomerRepository.search(searchRequest, pageable)).thenReturn(Collections.emptyList());

        List<IndividualCustomerSearchResponse> responses = individualCustomerService.searchCustomer(searchRequest, 0, 10);

        assertNotNull(responses);
        assertTrue(responses.isEmpty());

        verify(individualCustomerBusinessRules).individualCustomerSearchCheckExist(searchRequest);
        verify(individualCustomerRepository).search(searchRequest, pageable);
    }

    @Test
    void testAddCustomer_UniqueCustomerException() {
        doThrow(new BusinessException("Individual customer already exists"))
                .when(individualCustomerBusinessRules)
                .individualCustomerMustBeUnique(anyString(), anyInt());

        BusinessException exception = assertThrows(BusinessException.class,
                () -> individualCustomerService.addCustomer(request));

        assertEquals("Individual customer already exists", exception.getMessage());

        verify(individualCustomerRepository, never()).save(any(IndividualCustomer.class));
    }

    @Test
    void testGetCustomerById_CustomerNotFoundException() {
        int customerId = 123;

        doThrow(new NotFoundException("No customer found with id: " + customerId))
                .when(individualCustomerBusinessRules)
                .isIndividualCustomerExist(customerId);

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> individualCustomerService.getCustomerById(customerId));

        assertEquals("No customer found with id: " + customerId, exception.getMessage());

        verify(individualCustomerRepository, never()).findById(customerId);
    }


    private String generateRandomString(int minLength, int maxLength) {
        int length = new Random().nextInt((maxLength - minLength) + 1) + minLength;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }

        return result.toString();
    }

    private String generateRandomNumericString(int minLength, int maxLength) {
        int length = new Random().nextInt((maxLength - minLength) + 1) + minLength;
        String characters = "0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }

        return result.toString();
    }
}