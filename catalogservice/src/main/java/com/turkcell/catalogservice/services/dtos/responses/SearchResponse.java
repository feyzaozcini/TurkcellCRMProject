package com.turkcell.catalogservice.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponse {
    private Integer id;
    private String name;
    private Integer catalogId;
}
