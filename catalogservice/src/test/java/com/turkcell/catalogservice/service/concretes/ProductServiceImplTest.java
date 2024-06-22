package com.turkcell.catalogservice.service.concretes;
import com.turkcell.catalogservice.entities.Catalog;
import com.turkcell.catalogservice.entities.Product;
import com.turkcell.catalogservice.core.utils.types.NotFoundException;
import com.turkcell.catalogservice.repositories.CatalogRepository;
import com.turkcell.catalogservice.repositories.ProductRepository;
import com.turkcell.catalogservice.services.concretes.ProductServiceImpl;
import com.turkcell.catalogservice.services.dtos.requests.ProductAddRequest;
import com.turkcell.catalogservice.services.dtos.requests.ProductUpdateRequest;
import com.turkcell.catalogservice.services.dtos.requests.SearchRequest;
import com.turkcell.catalogservice.services.dtos.responses.CreatedProductResponse;
import com.turkcell.catalogservice.services.dtos.responses.ProductGetResponse;
import com.turkcell.catalogservice.services.dtos.responses.UpdatedProductResponse;
import com.turkcell.catalogservice.services.rules.ProductBusinessRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CatalogRepository catalogRepository;

    @Mock
    private ProductBusinessRules productBusinessRules;

    @InjectMocks
    private ProductServiceImpl productService;

    private ProductAddRequest productAddRequest;
    private Product product;
    private Catalog catalog;

    @BeforeEach
    void setUp() {
        productAddRequest = new ProductAddRequest("Internet", 100.0f, 1);
        catalog = new Catalog();
        catalog.setId(1);
        catalog.setProducts(new HashSet<>());

        product = new Product();
        product.setId(1);
        product.setName("Internet");
        product.setPrice(100.0f);
        product.setCatalog(catalog);
        product.setCreatedDate(LocalDateTime.now());
    }

    @Test
    void testAddProduct() {
        when(productBusinessRules.findCatalogById(productAddRequest.getCatalogId())).thenReturn(catalog);
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
            Product savedProduct = invocation.getArgument(0);
            savedProduct.setId(1);
            return savedProduct;
        });
        when(catalogRepository.save(any(Catalog.class))).thenReturn(catalog);

        CreatedProductResponse response = productService.addProduct(productAddRequest);

        assertNotNull(response);
        assertEquals(product.getId(), response.getId());
        assertEquals(product.getName(), response.getName());

        verify(productBusinessRules).findCatalogById(productAddRequest.getCatalogId());
        verify(productRepository).save(any(Product.class));
        verify(catalogRepository).save(any(Catalog.class));
    }


    @Test
    void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));

        List<ProductGetResponse> responses = productService.getAllProducts();

        assertNotNull(responses);
        assertFalse(responses.isEmpty());
        assertEquals(product.getId(), responses.get(0).getId());

        verify(productRepository).findAll();
    }

    @Test
    void testGetProductById() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        ProductGetResponse response = productService.getProductById(product.getId());

        assertNotNull(response);
        assertEquals(product.getId(), response.getId());

        verify(productRepository).findById(product.getId());
    }

    @Test
    void testDeleteProductById() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        productService.deleteProductById(product.getId());

        verify(productBusinessRules).checkIfProductExistsById(product.getId());
        verify(productRepository).save(product);
    }

    @Test
    void testUpdateProduct() {
        ProductUpdateRequest updateRequest = new ProductUpdateRequest();
        updateRequest.setId(product.getId());
        updateRequest.setCatalogId(catalog.getId());
        updateRequest.setName("Updated Product");
        updateRequest.setPrice(150.0f);

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productBusinessRules.findCatalogById(updateRequest.getCatalogId())).thenReturn(catalog);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        UpdatedProductResponse response = productService.updateProduct(updateRequest);

        assertNotNull(response);
        assertEquals(product.getId(), response.getId());

        verify(productRepository).findById(product.getId());
        verify(productBusinessRules).findCatalogById(updateRequest.getCatalogId());
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void testSearchProduct() {
        SearchRequest searchRequest = new SearchRequest();
        when(productRepository.search(searchRequest)).thenReturn(Collections.emptyList());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> productService.search(searchRequest));

        assertEquals("There is no product matching the information you entered. Please check.", exception.getMessage());

        verify(productRepository).search(searchRequest);
    }

}
