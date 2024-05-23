package com.turkcell.customerservice.repositories;

import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.services.dtos.request.SearchRequest;
import com.turkcell.customerservice.services.dtos.response.SearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsById(int id);
    @Query("select new com.turkcell.customerservice.services.dtos.response." +
        "SearchResponse(c.id, c.firstName, c.lastName, c.secondName, c.nationalityId)" +
        " from Customer c" +
        "  where (c.firstName like concat('%',:#{#request.getFirstName()},'%')) " +
        " or ( c.lastName like concat('%',:#{#request.getLastName()},'%')) " +
        " or ( c.nationalityId = :#{#request.getNationalityId()}) " +
        " or ( c.accountNumber = :#{#request.getAccountNumber()}) " +
        " or ( c.gsmNumber = :#{#request.getGsmNumber()}) " +
        " or ( c.orderNumber = :#{#request.getOrderNumber()}) " +
        " or ( c.id = :#{#request.getCustomerId()}) ")
        List<SearchResponse> search(SearchRequest request);
}
