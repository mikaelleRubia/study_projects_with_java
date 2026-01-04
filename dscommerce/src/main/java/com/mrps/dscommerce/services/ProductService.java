package com.mrps.dscommerce.services;

import com.mrps.dscommerce.dto.ProductDTO;
import com.mrps.dscommerce.entities.Product;
import com.mrps.dscommerce.repositories.ProductRepository;
import jakarta.persistence.Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Optional<Product> result = repository.findById(id);
        if (result.isPresent()) {
            Product product = result.get();
            return new ProductDTO(product);
        }
        return null;

    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable page) {
        Page<Product> result = repository.findAll(page);
        return result.map(x -> new ProductDTO(x));

    }

    @Transactional()
    public ProductDTO insert(ProductDTO productDTO) {
        Product product = new Product();
        copyDtoEntity(productDTO, product);

        product = repository.save(product);

        return new ProductDTO(product);

    }

    @Transactional()
    public ProductDTO update(ProductDTO productDTO, Long id) {
        Product product = repository.getReferenceById(id);
        copyDtoEntity(productDTO, product);
        product = repository.save(product);
        return new ProductDTO(product);

    }

    public void copyDtoEntity(ProductDTO productDTO, Product entity){
        entity.setName(productDTO.getName());
        entity.setDescription(productDTO.getDescription());
        entity.setPrice(productDTO.getPrice());
        entity.setImgURL(productDTO.getImgURL());

    }

}
