package com.example.arsenalfinalproject.service;

import com.example.arsenalfinalproject.model.binding.ProductAddBindingModel;
import com.example.arsenalfinalproject.model.entity.ProductEntity;
import com.example.arsenalfinalproject.model.service.ProductAddServiceModel;
import com.example.arsenalfinalproject.model.service.ProductUpdateServiceModel;
import com.example.arsenalfinalproject.model.view.ProductsViewModel;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void initializeProduct();

    List<ProductsViewModel> getAllProducts();

    ProductsViewModel findById(Long id);

    void updateOffer(ProductUpdateServiceModel productUpdateServiceModel);

    ProductAddServiceModel addOffer(ProductAddBindingModel productAddBindingModel, String userIdentifier);

    void deleteProduct(Long id);
}
