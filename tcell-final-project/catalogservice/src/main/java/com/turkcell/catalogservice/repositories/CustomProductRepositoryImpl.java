package com.turkcell.catalogservice.repositories;

import com.turkcell.catalogservice.entities.Product;
import com.turkcell.catalogservice.services.dtos.requests.SearchRequest;
import com.turkcell.catalogservice.services.dtos.responses.SearchResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class CustomProductRepositoryImpl implements CustomProductRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<SearchResponse> search(SearchRequest request) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SearchResponse> cq = cb.createQuery(SearchResponse.class);
        Root<Product> product = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (request.getId() != 0) {
            predicates.add(cb.equal(product.get("id"), request.getId()));
        }

        if (request.getName() != null && !request.getName().isEmpty()) {
            predicates.add(cb.like(product.get("name"), "%" + request.getName() + "%"));
        }

        if (request.getCatalogId() != 0) {
            predicates.add(cb.equal(product.get("catalog").get("id"), request.getCatalogId()));
        }

        cq.select(cb.construct(SearchResponse.class,
                product.get("id"),
                product.get("name"),
                product.get("catalog").get("id")
        )).where(cb.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(cq).getResultList();
    }
}
