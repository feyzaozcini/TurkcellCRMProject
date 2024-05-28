package com.turkcell.catalogservice.services.concretes;

import com.turkcell.catalogservice.repositories.CatalogRepository;
import com.turkcell.catalogservice.services.abstracts.CatalogService;
import com.turkcell.catalogservice.services.dtos.responses.CatalogGetResponse;
import com.turkcell.catalogservice.services.mappers.CatalogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private final CatalogRepository catalogRepository;

    public List<CatalogGetResponse> getAllCatalogs(){
        return catalogRepository.findAll().stream().map((catalog)-> CatalogMapper.INSTANCE.getResponseFromCatalog(catalog)).collect(Collectors.toList());
    }
    public CatalogGetResponse getCatalogById(int id){
        return CatalogMapper.INSTANCE.getResponseFromCatalog(catalogRepository.findById(id).orElseThrow());
    }
}
