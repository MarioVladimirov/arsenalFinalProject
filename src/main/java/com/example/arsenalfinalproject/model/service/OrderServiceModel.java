package com.example.arsenalfinalproject.model.service;

import com.example.arsenalfinalproject.model.entity.UserEntity;

import java.math.BigDecimal;

public class OrderServiceModel {

    private String userName;
    private String  productName;
    private String  urlPicture;
    private String address;
    private String mobilePhone;
    private String name;
    private String description;
    private Integer count;
    private BigDecimal totalSum;
    private BigDecimal price;


    public OrderServiceModel() {
    }


    public String getUserName() {
        return userName;
    }

    public OrderServiceModel setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public OrderServiceModel setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrderServiceModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public OrderServiceModel setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public OrderServiceModel setCount(Integer count) {
        this.count = count;
        return this;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public OrderServiceModel setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
        return this;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public OrderServiceModel setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}



