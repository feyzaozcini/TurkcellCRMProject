package com.turkcell.customerservice.repositories;

import com.turkcell.customerservice.entities.IndividualCustomer;
import com.turkcell.customerservice.services.dtos.request.IndividualCustomerSearchRequest;
import com.turkcell.customerservice.services.dtos.response.IndividualCustomerSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, Integer> {
    boolean existsById(int id);
    boolean existsIndividualCustomerByNationalityId(Long nationalId);

    @Query("select new com.turkcell.customerservice.services.dtos.response.IndividualCustomerSearchResponse(" +
            "c.id, c.firstName, c.secondName, c.lastName, c.nationalityId)" +
            " from IndividualCustomer c" +
            " where c.active = true" +
            " and (" +
            " lower(c.firstName) like lower(concat(:#{#request.getFirstName()},'%'))" +
            " or lower(c.lastName) like lower(concat(:#{#request.getLastName()},'%'))" +
            " or c.nationalityId = :#{#request.getNationalityId()}" +
            " or c.accountNumber = :#{#request.getAccountNumber()}" +
            " or c.gsmNumber = :#{#request.getGsmNumber()}" +
            " or c.orderNumber = :#{#request.getOrderNumber()}" +
            " or c.id = :#{#request.getCustomerId()}" +
            ")")

    List<IndividualCustomerSearchResponse> search(IndividualCustomerSearchRequest request);
}
