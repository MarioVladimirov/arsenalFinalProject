package com.example.arsenalfinalproject.model.view;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderAllByOneUserViewModel {

    private String urlPicture;
    private String productName;
    private LocalDate dateByOrder;
    private BigDecimal totalSum;
    private String fullName;


    public OrderAllByOneUserViewModel() {
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public OrderAllByOneUserViewModel setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public OrderAllByOneUserViewModel setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public LocalDate getDateByOrder() {
        return dateByOrder;
    }

    public OrderAllByOneUserViewModel setDateByOrder(LocalDate dateByOrder) {
        this.dateByOrder = dateByOrder;
        return this;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public OrderAllByOneUserViewModel setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public OrderAllByOneUserViewModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
