package com.turkcell.catalogservice.controllers;

import com.turkcell.catalogservice.services.abstracts.CatalogService;
import com.turkcell.catalogservice.services.dtos.responses.CatalogGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;
    @GetMapping("/all")
    public List<CatalogGetResponse> getAllCatalogs(){
        return catalogService.getAllCatalogs();
    }
    @GetMapping("/{id}")
    public CatalogGetResponse getCatalogById(@PathVariable int id){
        return catalogService.getCatalogById(id);
    }
}
