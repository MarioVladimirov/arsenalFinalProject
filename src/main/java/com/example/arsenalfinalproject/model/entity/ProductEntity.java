package com.example.arsenalfinalproject.model.entity;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity{

    private PictureEntity picture;
    private String productName;
    private BigDecimal price;
    private Integer countProduct;


    public ProductEntity() {
    }

    @Column( nullable = false)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Column( nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(nullable = false)
    public Integer getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(Integer countProduct) {
        this.countProduct = countProduct;
    }

    @OneToOne(fetch = FetchType.EAGER)
    public PictureEntity getPicture() {
        return picture;
    }

    public void setPicture(PictureEntity picture) {
        this.picture = picture;
    }
}
