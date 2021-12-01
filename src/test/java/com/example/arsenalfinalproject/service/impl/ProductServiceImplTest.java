package com.example.arsenalfinalproject.service.impl;


import com.example.arsenalfinalproject.model.binding.ProductAddBindingModel;
import com.example.arsenalfinalproject.model.entity.PictureEntity;
import com.example.arsenalfinalproject.model.entity.ProductEntity;
import com.example.arsenalfinalproject.model.entity.RoleEntity;
import com.example.arsenalfinalproject.model.entity.UserEntity;
import com.example.arsenalfinalproject.model.entity.enums.RoleNameEnum;
import com.example.arsenalfinalproject.model.service.ProductAddServiceModel;
import com.example.arsenalfinalproject.model.service.ProductUpdateServiceModel;
import com.example.arsenalfinalproject.model.view.ProductsViewModel;
import com.example.arsenalfinalproject.repository.PictureRepository;
import com.example.arsenalfinalproject.repository.ProductRepository;
import com.example.arsenalfinalproject.service.CloudinaryService;
import com.example.arsenalfinalproject.service.PictureService;
import com.example.arsenalfinalproject.service.ProductService;
import com.example.arsenalfinalproject.service.UserService;
import com.example.arsenalfinalproject.web.exception.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {


    private ProductServiceImpl productServiceToTest;

    @Mock
    private ProductRepository mockProductRepository;

    @Mock
    private UserService userService;

    @Mock
    private PictureRepository pictureRepository;

    @Mock
    private CloudinaryServiceImpl cloudinaryService;

    @Mock
    private PictureServiceImpl pictureService;

    private final ModelMapper modelMapper = new ModelMapper();

    ProductEntity testProduct;
    private Object ProductEntity;


    @BeforeEach
    void init() {

        productServiceToTest = new ProductServiceImpl(mockProductRepository, userService, modelMapper, pictureRepository,
                cloudinaryService, pictureService);

        testProduct = new ProductEntity();
        testProduct.setCountProduct(10);
        testProduct.setProductName("testName");
        testProduct.setPrice(BigDecimal.valueOf(3.20));
        testProduct.setPicture(new PictureEntity());
        testProduct.setId(1L);


    }

    @Test
    void testFindByIdEntityCorrect() {

        Mockito.when(mockProductRepository.findById(testProduct.getId()))
                .thenReturn(Optional.of(testProduct));

        var actual = productServiceToTest.findByIdEntity(testProduct.getId());

        Assertions.assertEquals(testProduct.getProductName(), actual.getProductName());

    }

    @Test
    void testGetAllProducts() {

        Mockito.when(mockProductRepository.findAll())
                .thenReturn(List.of(testProduct));


        var actual = productServiceToTest.getAllProducts();

        Assertions.assertEquals(1, actual.size());


    }

    @Test
    void testFindByIdCorrect() {
        ProductsViewModel productsViewModel = new ProductsViewModel();


        Mockito.when(mockProductRepository.findById(testProduct.getId()))
                .thenReturn(Optional.of(testProduct));

        var actual = productServiceToTest.findById(testProduct.getId());

        Assertions.assertEquals(testProduct.getProductName(), actual.getProductName());

    }

    @Test
    void testUpdateOffer() {

        ProductUpdateServiceModel testServiceModel = new ProductUpdateServiceModel();
        testServiceModel.setId(1L);
        testServiceModel.setProductName("Serve");

        Mockito.when(mockProductRepository.save(testProduct))
                .thenReturn(testProduct);
        Mockito.when(mockProductRepository.findById(testServiceModel.getId()))
                .thenReturn(Optional.of(testProduct));


        productServiceToTest.updateOffer(testServiceModel);

        Assertions.assertEquals(testProduct.getProductName(),
                testServiceModel.getProductName());

    }

    @Test
    void testUpdateProductIdNotFound() {
        ProductUpdateServiceModel testServiceModel = new ProductUpdateServiceModel();
        testServiceModel.setId(1L);
        testServiceModel.setProductName("Serve");

        Assertions.assertThrows(
                ObjectNotFoundException.class,
                () -> productServiceToTest.updateOffer(testServiceModel)
        );
    }


    @Test
    void testIsExistId() {
        Mockito.when(mockProductRepository.save(testProduct)).thenReturn(testProduct);
        Mockito.when(mockProductRepository.existsById(testProduct.getId())).thenReturn(true);

        mockProductRepository.save(testProduct);

        boolean existId = productServiceToTest.isExistId(1L);

        Assertions.assertTrue(existId);
    }


    @Test
    void testFindByIdEntity() {

        Mockito.when(mockProductRepository.findById(testProduct.getId()))
                .thenReturn(Optional.of(testProduct));

        var actual = productServiceToTest.findByIdEntity(testProduct.getId());

        Assertions.assertEquals(testProduct.getCountProduct(), actual.getCountProduct());

    }


    @Test
    void testChangeCount() {
        Mockito.when(mockProductRepository.findById(testProduct.getId()))
                .thenReturn(Optional.of(testProduct));

        productServiceToTest.changeCount(1L,5);

        Assertions.assertEquals(5,
                mockProductRepository.findById(1L).get().getCountProduct());



    }

    @Test
    void testIsOwnerThrowTrue() {
        var adminRole = new RoleEntity();
        adminRole.setRole(RoleNameEnum.ADMIN);

        var  testUser = new UserEntity();
        testUser.setUsername("Mario");
        testUser.setEmail("mario@abv.bg");
        testUser.setPassword("12345");
        testUser.setRoles(Set.of(adminRole));

        Mockito.when(mockProductRepository.findById(testProduct.getId()))
                .thenReturn(Optional.of(testProduct));
        Mockito.when(userService.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));

        boolean actual = productServiceToTest.isOwner(testUser.getUsername(), testProduct.getId());

        Assertions.assertTrue(actual);
    }

    @Test
    void testIsOwnerThrowFalse() {
        var adminRole = new RoleEntity();
        adminRole.setRole(RoleNameEnum.ADMIN);

        var  testUser = new UserEntity();
        testUser.setUsername("Mario");
        testUser.setEmail("mario@abv.bg");
        testUser.setPassword("12345");
        testUser.setRoles(Set.of(adminRole));

        var  testUser2 = new UserEntity();
        Optional<ProductEntity> p = Optional.empty();
        Mockito.when(mockProductRepository.findById(testProduct.getId()))
                .thenReturn(p);

        Mockito.when(userService.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser2));

        boolean actual = productServiceToTest.isOwner(testUser.getUsername(), testProduct.getId());

        Assertions.assertFalse(actual);
    }

    @Test
    void testAddProduct() throws IOException {

        ProductAddBindingModel testBinding = modelMapper.map(testProduct,ProductAddBindingModel.class);

        Mockito.when(mockProductRepository.save(Mockito.any(ProductEntity.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        ProductAddServiceModel productAddServiceModel = productServiceToTest.addProduct(testBinding);

        Assertions.assertEquals(testProduct.getProductName(),productAddServiceModel.getProductName());

    }


}


