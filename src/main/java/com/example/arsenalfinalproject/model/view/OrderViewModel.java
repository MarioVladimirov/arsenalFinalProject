package com.example.arsenalfinalproject.model.view;

import java.math.BigDecimal;

public class OrderViewModel {

    private String address;
    private String mobilePhone;
    private String name;
    private String description;
    private String urlPicture;
    private String productName;
    private BigDecimal price;
    private Integer count;
    private BigDecimal totalSum;
    private Integer maxCount;


    public OrderViewModel() {
    }

    public String getAddress() {
        return address;
    }

    public OrderViewModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public OrderViewModel setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderViewModel setDescription(String description) {
        this.description = description;
        return this;
    }


    public String getUrlPicture() {
        return urlPicture;
    }

    public OrderViewModel setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public OrderViewModel setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public OrderViewModel setCount(Integer count) {
        this.count = count;
        return this;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public OrderViewModel setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
        return this;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public OrderViewModel setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
        return this;
    }
}
