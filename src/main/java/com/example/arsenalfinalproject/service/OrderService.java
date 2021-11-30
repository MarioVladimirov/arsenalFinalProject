package com.example.arsenalfinalproject.service;

import com.example.arsenalfinalproject.model.binding.OrderBindingModel;
import com.example.arsenalfinalproject.model.view.OrderAllByOneUserViewModel;
import com.example.arsenalfinalproject.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {


    OrderViewModel findAllDataByIdAndCurrentUser(Long idProduct, String currentUser);

    void saveNewOrder(Long idProduct, OrderBindingModel orderBindingModel , String userName);


    List<OrderAllByOneUserViewModel> findAllOrderForOneUserByCurrentUser(String currentUsername);

    boolean isExistId(Long id);
}
