package com.turkcell.catalogservice.services.abstracts;

import com.turkcell.catalogservice.services.dtos.requests.ProductAddRequest;
import com.turkcell.catalogservice.services.dtos.requests.ProductUpdateRequest;
import com.turkcell.catalogservice.services.dtos.requests.SearchRequest;
import com.turkcell.catalogservice.services.dtos.responses.ProductGetResponse;
import com.turkcell.catalogservice.services.dtos.responses.SearchResponse;

import java.util.List;

public interface ProductService {
    void addProduct(ProductAddRequest request);
    List<ProductGetResponse> getAllProducts();
    ProductGetResponse getProductById(int id);
    void updateProduct(ProductUpdateRequest request);
    void deleteProductById(int id);
    List<SearchResponse> search(SearchRequest request);
    float getPriceById(int id);
}
