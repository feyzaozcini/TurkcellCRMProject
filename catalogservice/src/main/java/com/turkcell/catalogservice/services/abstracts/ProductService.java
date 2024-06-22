package com.turkcell.catalogservice.services.abstracts;

import com.turkcell.catalogservice.services.dtos.requests.ProductAddRequest;
import com.turkcell.catalogservice.services.dtos.requests.ProductUpdateRequest;
import com.turkcell.catalogservice.services.dtos.requests.SearchRequest;
import com.turkcell.catalogservice.services.dtos.responses.CreatedProductResponse;
import com.turkcell.catalogservice.services.dtos.responses.ProductGetResponse;
import com.turkcell.catalogservice.services.dtos.responses.SearchResponse;
import com.turkcell.catalogservice.services.dtos.responses.UpdatedProductResponse;

import java.util.List;

public interface ProductService {
    CreatedProductResponse addProduct(ProductAddRequest request);
    List<ProductGetResponse> getAllProducts();
    ProductGetResponse getProductById(int id);
    UpdatedProductResponse updateProduct(ProductUpdateRequest request);
    void deleteProductById(int id);
    List<SearchResponse> search(SearchRequest request);

}
