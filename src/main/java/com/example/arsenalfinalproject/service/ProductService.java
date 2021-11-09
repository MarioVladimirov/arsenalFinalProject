package com.example.arsenalfinalproject.service;

import com.example.arsenalfinalproject.model.entity.ProductEntity;
import com.example.arsenalfinalproject.model.view.ProductsViewModel;

import java.util.List;

public interface ProductService {
    void initializeProduct();

    List<ProductsViewModel> getAllProducts();
}
