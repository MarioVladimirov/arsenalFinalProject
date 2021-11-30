package com.example.arsenalfinalproject.model.binding;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class ProductUpdateBindingModel {

    private Long id;
    private String urlPicture;
    private String productName;
    private BigDecimal price;
    private Integer countProduct;


    public ProductUpdateBindingModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    @NotBlank
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Positive
    @NotNull
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @PositiveOrZero
    @NotNull
    public Integer getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(Integer countProduct) {
        this.countProduct = countProduct;
    }

}
