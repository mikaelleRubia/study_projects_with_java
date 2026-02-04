package com.mrps.dscommerce.services;

import com.mrps.dscommerce.dto.ProductDTO;
import com.mrps.dscommerce.entities.Product;
import com.mrps.dscommerce.repositories.ProductRepository;
import com.mrps.dscommerce.services.exceptions.DatabaseException;
import com.mrps.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ProductDTO(product);
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

    @Transactional
    public ProductDTO update(ProductDTO productDTO, Long id) {
        try {
            Product product = repository.getReferenceById(id);
            copyDtoEntity(productDTO, product);
            product = repository.save(product);
            return new ProductDTO(product);
        }
        catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(" Recurso não encontrado");
        }
    }

    public void copyDtoEntity(ProductDTO productDTO, Product entity){
        entity.setName(productDTO.getName());
        entity.setDescription(productDTO.getDescription());
        entity.setPrice(productDTO.getPrice());
        entity.setImgURL(productDTO.getImgURL());

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException(" Recurso não encontrado");
        }
        try{
            repository.deleteById(id);
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

}
