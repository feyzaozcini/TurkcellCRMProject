package com.turkcell.catalogservice.services.concretes;

import com.turkcell.catalogservice.core.utils.types.NotFoundException;
import com.turkcell.catalogservice.entities.Catalog;
import com.turkcell.catalogservice.entities.Product;
import com.turkcell.catalogservice.repositories.CatalogRepository;
import com.turkcell.catalogservice.repositories.ProductRepository;
import com.turkcell.catalogservice.services.abstracts.ProductService;
import com.turkcell.catalogservice.services.dtos.requests.ProductAddRequest;
import com.turkcell.catalogservice.services.dtos.requests.ProductUpdateRequest;
import com.turkcell.catalogservice.services.dtos.requests.SearchRequest;
import com.turkcell.catalogservice.services.dtos.responses.CreatedProductResponse;
import com.turkcell.catalogservice.services.dtos.responses.ProductGetResponse;
import com.turkcell.catalogservice.services.dtos.responses.SearchResponse;
import com.turkcell.catalogservice.services.dtos.responses.UpdatedProductResponse;
import com.turkcell.catalogservice.services.mappers.ProductMapper;
import com.turkcell.catalogservice.services.rules.ProductBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CatalogRepository catalogRepository;
    private final ProductBusinessRules productBusinessRules;
    public CreatedProductResponse addProduct(ProductAddRequest request) {

        Catalog catalog = productBusinessRules.findCatalogById(request.getCatalogId());
        Product product = ProductMapper.INSTANCE.productFromAddRequest(request);
        product.setCatalog(catalog);
        product.setCreatedDate(LocalDateTime.now());
        productRepository.save(product);
        catalog.getProducts().add(product);
        catalogRepository.save(catalog);

        return ProductMapper.INSTANCE.getResponseFromCreatedProduct(product);
    }

    public List<ProductGetResponse> getAllProducts(){
        return productRepository.findAll().stream().map((product)-> ProductMapper.INSTANCE.getResponseFromProduct(product)).collect(Collectors.toList());
    }

    public ProductGetResponse getProductById(int id){
        return ProductMapper.INSTANCE.getResponseFromProduct(productRepository.findById(id).orElseThrow(()->new NotFoundException("Ilgili product bulunamadi")));
    }

    public void deleteProductById(int id){
        productBusinessRules.checkIfProductExistsById(id);
        productRepository.deleteById(id);
    }

    public UpdatedProductResponse updateProduct(ProductUpdateRequest request) {
        Catalog catalog=productBusinessRules.findCatalogById(request.getCatalogId());
        Product product = productRepository.findById(request.getId()).orElseThrow();
        product.setUpdatedDate(LocalDateTime.now());
        product.setCatalog(catalog);
        Product savedProduct = productRepository.save(ProductMapper.INSTANCE.productFromUpdateRequest(request));
        return ProductMapper.INSTANCE.getResponseFromUpdatedProduct(savedProduct);
    }

    public List<SearchResponse> search(SearchRequest request){
        List<SearchResponse> results = productRepository.search(request);
        if(results.isEmpty())
            throw new NotFoundException("There is no product matching the information you entered. Please check.");
        return results;
    }

    public float getPriceById(int id){
        return productRepository.findById(id).orElseThrow().getPrice();
    }
}
