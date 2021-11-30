package com.example.arsenalfinalproject.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    private UserEntity user;
    //  private ProductEntity product;
    private String productName;
    private String address;
    private String mobilePhone;
    private String name;
    private String description;
    private Integer count;
    private BigDecimal totalSum;
    private LocalDate dateByOrder;
    private String productUrl;

    public OrderEntity() {
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }


    @Column(nullable = false)
    public String getAddress() {
        return address;
    }

    public OrderEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    @Column(nullable = false)
    public String getMobilePhone() {
        return mobilePhone;
    }

    public OrderEntity setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }


    @Column(nullable = false)
    @Lob
    public String getDescription() {
        return description;
    }

    public OrderEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(nullable = false)
    public Integer getCount() {
        return count;
    }

    public OrderEntity setCount(Integer count) {
        this.count = count;
        return this;
    }

    @Column(nullable = false)
    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public OrderEntity setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
        return this;
    }

    @Column(nullable = false)
    public LocalDate getDateByOrder() {
        return dateByOrder;
    }

    public OrderEntity setDateByOrder(LocalDate dateByOrder) {
        this.dateByOrder = dateByOrder;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    @Column(nullable = false)
    public OrderEntity setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public OrderEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public OrderEntity setProductUrl(String productUrl) {
        this.productUrl = productUrl;
        return this;
    }
}
