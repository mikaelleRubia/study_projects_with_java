package com.mrps.dscommerce.dto;

import com.mrps.dscommerce.entities.Product;


public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgURL;

    public ProductDTO( Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgURL = product.getImgURL();
    }
    public ProductDTO(Long id, String name, String description, Double price, String imgURL) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgURL = imgURL;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgURL() {
        return imgURL;
    }
}
