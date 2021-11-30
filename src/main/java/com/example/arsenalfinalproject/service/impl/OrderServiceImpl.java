package com.example.arsenalfinalproject.service.impl;

import com.example.arsenalfinalproject.model.binding.OrderBindingModel;
import com.example.arsenalfinalproject.model.entity.OrderEntity;
import com.example.arsenalfinalproject.model.entity.ProductEntity;
import com.example.arsenalfinalproject.model.entity.UserEntity;
import com.example.arsenalfinalproject.model.service.OrderServiceModel;
import com.example.arsenalfinalproject.model.view.OrderAllByOneUserViewModel;
import com.example.arsenalfinalproject.model.view.OrderViewModel;
import com.example.arsenalfinalproject.model.view.ProductsViewModel;
import com.example.arsenalfinalproject.repository.OrderRepository;
import com.example.arsenalfinalproject.service.OrderService;
import com.example.arsenalfinalproject.service.ProductService;
import com.example.arsenalfinalproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {


    private final ProductService productService;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(ProductService productService, UserService userService, OrderRepository orderRepository, ModelMapper modelMapper) {

        this.productService = productService;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderViewModel findAllDataByIdAndCurrentUser(Long idProduct, String currentUser) {

        OrderViewModel orderViewModel = new OrderViewModel();

        ProductsViewModel productsViewModel = productService.findById(idProduct);

        orderViewModel.setUrlPicture(productsViewModel.getUrlPicture());

        orderViewModel.setProductName(productsViewModel.getProductName());

        UserEntity userEntity = userService.findByUsername(currentUser).get();
        orderViewModel.setName(userEntity.getFirstName() + " " + userEntity.getLastName());

        orderViewModel.setPrice(productsViewModel.getPrice());

        orderViewModel.setMaxCount(productsViewModel.getCountProduct());

        return orderViewModel;
    }

    @Override
    public void saveNewOrder(Long idProduct, OrderBindingModel orderBindingModel , String userName) {

        UserEntity userEntity = userService.findByUsername(userName).orElseThrow();

        //BigDecimal price = productService.findById(idProduct).getPrice();
        productService.changeCount(idProduct , orderBindingModel.getCount());


        OrderServiceModel orderServiceModel =
                modelMapper.map(orderBindingModel,OrderServiceModel.class);

        orderServiceModel.setTotalSum(orderServiceModel.getPrice().multiply(BigDecimal.valueOf(orderBindingModel.getCount())));

        OrderEntity newOrder = modelMapper.map(orderServiceModel , OrderEntity.class);

        newOrder.setUser(userEntity);
        newOrder.setDateByOrder(LocalDate.now());
        newOrder.setProductUrl(orderServiceModel.getUrlPicture());

      //  newOrder.setProduct(buyProduct);

        orderRepository.save(newOrder);

    }

    @Override
    public List<OrderAllByOneUserViewModel> findAllOrderForOneUserByCurrentUser(String currentUsername) {

    return     orderRepository
                .findAll()
                .stream()
                .filter(c -> c.getUser().getUsername().equals(currentUsername))
                .map(orderEntity -> {
                    OrderAllByOneUserViewModel orderAllByOneUserViewModel = new OrderAllByOneUserViewModel();
                    orderAllByOneUserViewModel
                            .setUrlPicture(orderEntity.getProductUrl())
                            .setProductName(orderEntity.getProductName())
                            .setDateByOrder(orderEntity.getDateByOrder())
                            .setTotalSum(orderEntity.getTotalSum())
                            .setFullName(orderEntity.getUser().getFirstName() + " " + orderEntity.getUser().getLastName());

                    return orderAllByOneUserViewModel;
                })
                .collect(Collectors.toList());

    }

    @Override
    public boolean isExistId(Long id) {
        return orderRepository.existsById(id);
    }
}
