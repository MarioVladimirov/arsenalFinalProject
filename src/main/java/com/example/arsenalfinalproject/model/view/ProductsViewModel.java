package com.example.arsenalfinalproject.model.view;

import java.math.BigDecimal;

public class ProductsViewModel {

    private Long id;
    private String urlPicture;
    private String publicId;
    private String productName;
    private BigDecimal price;
    private Integer countProduct;

    public ProductsViewModel() {
    }

    public Long getId() {
        return id;
    }

    public ProductsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public ProductsViewModel setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public ProductsViewModel setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public ProductsViewModel setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductsViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getCountProduct() {
        return countProduct;
    }

    public ProductsViewModel setCountProduct(Integer countProduct) {
        this.countProduct = countProduct;
        return this;
    }
}
