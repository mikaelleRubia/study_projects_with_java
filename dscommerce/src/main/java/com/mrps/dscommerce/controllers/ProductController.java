package com.mrps.dscommerce.controllers;

import com.mrps.dscommerce.dto.ProductDTO;
import com.mrps.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping(value= "/{id}")
    public ProductDTO findById(@PathVariable Long id){
        ProductDTO productDTO = service.findById(id);
        return productDTO;
    }

    @GetMapping()
    public Page<ProductDTO> findAll(Pageable page){
        Page<ProductDTO> results = service.findAll(page);
        return results;
    }
}
