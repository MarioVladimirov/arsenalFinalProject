package com.example.arsenalfinalproject.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity{

    private UserEntity user;
    private String  productName;
    private String address;
    private String mobilePhone;
    private String name;
    private String description;
    private Integer count;
    private BigDecimal totalSum;
    private LocalDate dateByOrder;

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
    public String getProductName() {
        return productName;
    }

    public OrderEntity setProductName(String productName) {
        this.productName = productName;
        return this;
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
    public String getName() {
        return name;
    }

    public OrderEntity setName(String name) {
        this.name = name;
        return this;
    }
    @Column(columnDefinition = "TEXT")
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
}
