package com.example.arsenalfinalproject.model.service;

import java.math.BigDecimal;

public class ProductUpdateServiceModel {

    private Long id;
    private String productName;
    private BigDecimal price;
    private Integer countProduct;


    public ProductUpdateServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(Integer countProduct) {
        this.countProduct = countProduct;
    }
}
