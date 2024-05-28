package com.turkcell.catalogservice.services.concretes;

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
    public void addProduct(ProductAddRequest request){
//        Product product = ProductMapper.INSTANCE.productFromAddRequest(request);
//        productRepository.save(product);
//        Catalog catalog = catalogRepository.findById(request.getCatalogId()).orElseThrow();
//        Set<Product> catalogProducts = catalog.getProducts();
//        catalogProducts.add(product);
//        catalog.setProducts(catalogProducts);
//        catalogRepository.save(catalog);
        Catalog catalog = catalogRepository.findById(request.getCatalogId()).orElseThrow(() -> new RuntimeException("Catalog not found"));

        // Product nesnesini oluştur ve Catalog ile ilişkilendir
        Product product = ProductMapper.INSTANCE.productFromAddRequest(request);
        product.setCatalog(catalog);

        // Product nesnesini kaydet
        productRepository.save(product);

        // Catalog nesnesinin products kümesini güncelle
        catalog.getProducts().add(product);

        // Catalog nesnesini güncelle
        catalogRepository.save(catalog);
    }

    public List<ProductGetResponse> getAllProducts(){
        return productRepository.findAll().stream().map((product)-> ProductMapper.INSTANCE.getResponseFromProduct(product)).collect(Collectors.toList());
    }

    public ProductGetResponse getProductById(int id){
        return ProductMapper.INSTANCE.getResponseFromProduct(productRepository.findById(id).orElseThrow());
    }

    public void deleteProductById(int id){
        productRepository.deleteById(id);
    }

    public void updateProduct(ProductUpdateRequest request){
        productRepository.save(ProductMapper.INSTANCE.productFromUpdateRequest(request));
    }

    public List<SearchResponse> search(SearchRequest request){
        return productRepository.search(request);
    }
}
