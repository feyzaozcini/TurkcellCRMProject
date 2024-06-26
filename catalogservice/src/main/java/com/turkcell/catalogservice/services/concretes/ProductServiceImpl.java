package com.turkcell.catalogservice.services.concretes;
import com.turkcell.catalogservice.core.utils.types.NotFoundException;
import com.turkcell.catalogservice.entities.BaseEntity;
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
        product.setActive(true);
        productRepository.save(product);
        catalog.getProducts().add(product);
        catalogRepository.save(catalog);
        return ProductMapper.INSTANCE.getResponseFromCreatedProduct(product);
    }
    public List<ProductGetResponse> getAllProducts(){
        productBusinessRules.checkIfAnyProductIsExist();
        return productRepository.findAll().stream().filter(BaseEntity::getActive).map((product)-> ProductMapper.INSTANCE.getResponseFromProduct(product)).collect(Collectors.toList());
    }

    public ProductGetResponse getProductById(int id){
        productBusinessRules.checkIfProductExistsById(id);
        return ProductMapper.INSTANCE.getResponseFromProduct(productRepository.findById(id).orElseThrow(()->new NotFoundException("Ilgili product bulunamadi")));
    }

    public void deleteProductById(int id){
        productBusinessRules.checkIfProductExistsById(id);
        Product product =productRepository.findById(id).orElseThrow();
        product.setActive(false);
        product.setDeletedDate(LocalDateTime.now());
        productRepository.save(product);
    }

    public UpdatedProductResponse updateProduct(ProductUpdateRequest request) {
        productBusinessRules.checkIfProductExistsById(request.getId());
        Product product = productRepository.findById(request.getId()).orElseThrow();
        if(request.getCatalogId() != null){
            Catalog catalog=productBusinessRules.findCatalogById(request.getCatalogId());
            product.setCatalog(catalog);
        }
        product.setUpdatedDate(LocalDateTime.now());
        ProductMapper.INSTANCE.productFromUpdateRequest(request, product);
        productRepository.save(product);
        return ProductMapper.INSTANCE.getResponseFromUpdatedProduct(product);
    }

    public List<SearchResponse> search(SearchRequest request){
        List<SearchResponse> results = productRepository.search(request);
        results.removeIf(response -> productRepository.existsByIdAndActive(response.getId(), false));
        if(results.isEmpty())
            throw new NotFoundException("There is no product matching the information you entered. Please check.");
        return results;
    }

}
