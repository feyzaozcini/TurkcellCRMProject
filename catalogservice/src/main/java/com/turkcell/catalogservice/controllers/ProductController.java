package com.turkcell.catalogservice.controllers;

import com.turkcell.catalogservice.services.abstracts.ProductService;
import com.turkcell.catalogservice.services.dtos.requests.ProductAddRequest;
import com.turkcell.catalogservice.services.dtos.requests.ProductUpdateRequest;
import com.turkcell.catalogservice.services.dtos.requests.SearchRequest;
import com.turkcell.catalogservice.services.dtos.responses.CreatedProductResponse;
import com.turkcell.catalogservice.services.dtos.responses.ProductGetResponse;
import com.turkcell.catalogservice.services.dtos.responses.SearchResponse;
import com.turkcell.catalogservice.services.dtos.responses.UpdatedProductResponse;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping("/add")
    public CreatedProductResponse addProduct(@Valid @RequestBody ProductAddRequest request){
        return productService.addProduct(request);
    }
    @GetMapping("/all")
    public List<ProductGetResponse> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public ProductGetResponse getProductById(@PathVariable int id){
        return productService.getProductById(id);
    }

    @PutMapping("/update")
    public UpdatedProductResponse updateProduct(@Valid @RequestBody ProductUpdateRequest request){
        return productService.updateProduct(request);
    }

    @PostMapping("/search")
    public ResponseEntity<List<SearchResponse>> searchProducts(@RequestBody SearchRequest request) {
        List<SearchResponse> results = productService.search(request);
        return ResponseEntity.ok(results);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable int id){
        productService.deleteProductById(id);
    }
}
