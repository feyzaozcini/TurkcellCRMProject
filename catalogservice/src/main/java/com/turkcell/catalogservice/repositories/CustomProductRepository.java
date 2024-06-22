package com.turkcell.catalogservice.repositories;

import com.turkcell.catalogservice.services.dtos.requests.SearchRequest;
import com.turkcell.catalogservice.services.dtos.responses.SearchResponse;

import java.util.List;
public interface CustomProductRepository {
    List<SearchResponse> search(SearchRequest request);
}
