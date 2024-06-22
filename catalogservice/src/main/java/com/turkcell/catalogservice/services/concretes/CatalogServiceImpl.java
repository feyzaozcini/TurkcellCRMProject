package com.turkcell.catalogservice.services.concretes;

import com.turkcell.catalogservice.core.utils.types.NotFoundException;
import com.turkcell.catalogservice.entities.BaseEntity;
import com.turkcell.catalogservice.entities.Catalog;
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
        List<Catalog> catalogs = catalogRepository.findAll().stream().filter(BaseEntity::getActive).collect(Collectors.toList());
        if(!catalogs.isEmpty())
            return catalogs.stream().filter(BaseEntity::getActive).map((catalog)-> CatalogMapper.INSTANCE.getResponseFromCatalog(catalog)).collect(Collectors.toList());
        else
            throw new NotFoundException("Hicbir catalog bulunamadi!");
    }
    public CatalogGetResponse getCatalogById(int id){
        return CatalogMapper.INSTANCE.getResponseFromCatalog(catalogRepository.findById(id).filter(BaseEntity::getActive).orElseThrow(()->new NotFoundException("Ilgili catalog bulunamadi!")));
    }
}
