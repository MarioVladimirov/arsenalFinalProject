package com.example.arsenalfinalproject.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class OrderBindingModel {


    private String address;
    private String mobilePhone;
    private String name;
    private String description;
    private String urlPicture;
    private BigDecimal price;
    private String productName;
    private Integer count;


    public OrderBindingModel() {
    }

    @NotBlank
    public String getAddress() {
        return address;
    }

    public OrderBindingModel setAddress(String address) {
        this.address = address;
        return this;
    }
    @NotBlank
    public String getMobilePhone() {
        return mobilePhone;
    }

    public OrderBindingModel setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }
    @NotBlank
    public String getName() {
        return name;
    }

    public OrderBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public OrderBindingModel setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public OrderBindingModel setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    @Positive

    public Integer getCount() {
        return count;
    }

    public OrderBindingModel setCount(Integer count) {
        this.count = count;
        return this;
    }

}
