package com.mrps.dscommerce.controllers;

import com.mrps.dscommerce.dto.ProductDTO;
import com.mrps.dscommerce.entities.Product;
import com.mrps.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ProductDTO insert(@RequestBody ProductDTO dto){

        return service.insert(dto);
    }
}
