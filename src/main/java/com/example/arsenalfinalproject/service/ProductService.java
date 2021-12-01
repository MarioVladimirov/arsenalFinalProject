package com.example.arsenalfinalproject.service;

import com.example.arsenalfinalproject.model.binding.ProductAddBindingModel;
import com.example.arsenalfinalproject.model.entity.ProductEntity;
import com.example.arsenalfinalproject.model.service.ProductAddServiceModel;
import com.example.arsenalfinalproject.model.service.ProductUpdateServiceModel;
import com.example.arsenalfinalproject.model.view.ProductsViewModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    void initializeProduct() throws IOException;

    List<ProductsViewModel> getAllProducts();

    ProductsViewModel findById(Long id);

    void updateOffer(ProductUpdateServiceModel productUpdateServiceModel);

    ProductAddServiceModel addProduct(ProductAddBindingModel productAddBindingModel) throws IOException;

    void deleteProduct(Long id , String publicId);

    boolean isOwner(String userName , Long id);

//    boolean isAdmin(String name);

    void changeCount(Long idProduct, Integer count);

    ProductEntity findByIdEntity(Long idProduct);

    boolean isExistId(Long id);
}
