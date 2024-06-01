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
import com.turkcell.catalogservice.services.dtos.responses.ProductGetResponse;
import com.turkcell.catalogservice.services.dtos.responses.SearchResponse;
import com.turkcell.catalogservice.services.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CatalogRepository catalogRepository;
    public void addProduct(ProductAddRequest request) {
        Catalog catalog = catalogRepository.findById(request.getCatalogId()).orElseThrow(() -> new NotFoundException("Ilgili catalog bulunamadi"));
        Product product = ProductMapper.INSTANCE.productFromAddRequest(request);
        product.setCatalog(catalog);
        productRepository.save(product);
        catalog.getProducts().add(product);
        catalogRepository.save(catalog);
    }

    public List<ProductGetResponse> getAllProducts(){
        return productRepository.findAll().stream().map((product)-> ProductMapper.INSTANCE.getResponseFromProduct(product)).collect(Collectors.toList());
    }

    public ProductGetResponse getProductById(int id){
        return ProductMapper.INSTANCE.getResponseFromProduct(productRepository.findById(id).orElseThrow(()->new NotFoundException("Ilgili product bulunamadi")));
    }

    public void deleteProductById(int id){
        if(productRepository.existsById(id))
            productRepository.deleteById(id);
        else
            throw new NotFoundException("Ilgili catalog bulunamadi!");
    }

    public void updateProduct(ProductUpdateRequest request){
        productRepository.save(ProductMapper.INSTANCE.productFromUpdateRequest(request));
    }

    public List<SearchResponse> search(SearchRequest request){
        List<SearchResponse> results = productRepository.search(request);
        if(results.isEmpty())
            throw new NotFoundException("There is no product matching the information you entered. Please check.");
        return results;
    }
}