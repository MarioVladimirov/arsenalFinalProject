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
import com.example.arsenalfinalproject.service.*;

import com.example.arsenalfinalproject.web.exception.ObjectNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final PictureRepository pictureRepository;
    private final CloudinaryService cloudinaryService;
    private final PictureService pictureService;


    public ProductServiceImpl(ProductRepository productRepository, UserService userService, ModelMapper modelMapper, PictureRepository pictureRepository, CloudinaryService pictureService, PictureService pictureService1) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.pictureRepository = pictureRepository;
        this.cloudinaryService = pictureService;
        this.pictureService = pictureService1;
    }




    @Override
    public void initializeProduct() throws IOException {

        if (productRepository.count() == 0) {

            ProductEntity product1 = new ProductEntity();
            product1.setCountProduct(30);
            product1.setPrice(BigDecimal.valueOf(3));
            PictureEntity pictureEntity =
                    pictureService.createPictureEntityByPathInit("src/main/resources/static/images/shop/cards.jpg");
            product1.setPicture(pictureEntity);

            product1.setProductName("Playing cards \"Arsenal Bulgaria");

//            ProductEntity product2 = new ProductEntity();
//            product2.setCountProduct(25);
//            product2.setPrice(BigDecimal.valueOf(8));
//            product2.setProductName("Key ring \"15 years ASCB\"");
//            product2.setPicture( pictureService.createPictureEntityByPathInit
//                    ("src/main/resources/static/images/shop/keyRing.jpg"));
//
//
//            ProductEntity product3 = new ProductEntity();
//            product3.setCountProduct(65);
//            product3.setPrice(BigDecimal.valueOf(10));
//            product3.setProductName("Silicone bracelet \"Arsenal Bulgaria\"");
//            product3.setPicture( pictureService.createPictureEntityByPathInit
//                    ("src/main/resources/static/images/shop/siliconeBracelet.jpg"));
//
//            ProductEntity product4 = new ProductEntity();
//            product4.setCountProduct(15);
//            product4.setPrice(BigDecimal.valueOf(35));
//            product4.setProductName("Backpack \"Arsenal Bulgaria\"");
//            product4.setPicture( pictureService.createPictureEntityByPathInit
//                    ("src/main/resources/static/images/shop/backpack.jpg"));
//
//
//            ProductEntity product5 = new ProductEntity();
//            product5.setCountProduct(25);
//            product5.setPrice(BigDecimal.valueOf(29));
//            product5.setProductName("Т-shirt \"Bulgarian Gooners\"");
//            product5.setPicture( pictureService.createPictureEntityByPathInit
//                    ("src/main/resources/static/images/shop/tshirtPolo.jpg"));
//
//            ProductEntity product6 = new ProductEntity();
//            product6.setCountProduct(55);
//            product6.setPrice(BigDecimal.valueOf(68));
//            product6.setProductName("Ian Wright - A Life In Football");
//            product6.setPicture( pictureService.createPictureEntityByPathInit
//                    ("src/main/resources/static/images/shop/book.jpg"));

//            ProductEntity membership = new ProductEntity();
//            membership.setCountProduct(15000);
//            membership.setPrice(BigDecimal.valueOf(30));
//            PictureEntity membershipPic =
//                    pictureService.createPictureEntityByPathInit("src/main/resources/static/images/membership.png");
//            membership.setPicture(membershipPic);
//
//            membership.setProductName("Membership 2021-2022");


//            productRepository.save(product7);
            productRepository.save(product1);
//            productRepository.save(product2);
//            productRepository.save(product3);
//            productRepository.save(product4);
//            productRepository.save(product5);
//            productRepository.save(product6);


        }
    }

    @Override
    public List<ProductsViewModel> getAllProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(productEntity -> {
                            ProductsViewModel product = modelMapper.map(productEntity, ProductsViewModel.class);
                            String url = productEntity.getPicture().getUrl();
                            String publicID = productEntity.getPicture().getPublicId();
                            product
                                    .setUrlPicture(url)
                                    .setPublicId(publicID);
                            return product;
                        }
                )
                .collect(Collectors.toList());
    }

    @Override
    public ProductsViewModel findById(Long id) {

        return productRepository.findById(id)
                .map(this::mapDetailsProduct)
                .get();
    }

    @Override
    public void updateOffer(ProductUpdateServiceModel productUpdateServiceModel) {
        ProductEntity productEntity =
                productRepository.findById(productUpdateServiceModel.getId()).orElseThrow(() ->
                        new ObjectNotFoundException(productUpdateServiceModel.getId()));

        productEntity.setProductName(productUpdateServiceModel.getProductName());
        productEntity.setCountProduct(productUpdateServiceModel.getCountProduct());
        productEntity.setPrice(productUpdateServiceModel.getPrice());


        productRepository.save(productEntity);
    }

    @Override
    public ProductAddServiceModel addProduct(ProductAddBindingModel productAddBindingModel) throws IOException {

        ProductAddServiceModel productAddServiceModel =
                modelMapper.map(productAddBindingModel, ProductAddServiceModel.class);

        ProductEntity newProduct = modelMapper.map(productAddServiceModel, ProductEntity.class);

        PictureEntity pictureEntity = pictureService.createPictureEntity(productAddBindingModel.getPicture());

        newProduct.setPicture(pictureEntity);
        ProductEntity savedProduct = productRepository.save(newProduct);

        return modelMapper.map(savedProduct, ProductAddServiceModel.class);
    }

    @Override
    public void deleteProduct(Long id , String publicId) {
        productRepository.deleteById(id);
        cloudinaryService.delete(publicId);
        pictureService.deletePictureByPublicId(publicId);
    }

    @Override
    public boolean isOwner(String userName, Long id) {

        Optional<ProductEntity> productOpt = productRepository
                .findById(id);

        Optional<UserEntity> caller = userService.findByUsername(userName);

        if (productOpt.isEmpty() || caller.isEmpty()) {
            return false;
        } else {
            return isAdmin(caller.get());
        }
    }



    @Override
    public void changeCount(Long idProduct, Integer count) {
        Optional<ProductEntity> productOpt = productRepository
                .findById(idProduct);

        ProductEntity productEntity = productOpt.get();

        int currentCount = productEntity.getCountProduct() - count;



        productEntity.setCountProduct(currentCount);

       productRepository.save(productEntity);

    }
//return productRepository.getById(idProduct);
    @Override
    public ProductEntity findByIdEntity(Long idProduct) {
        return productRepository.findById(idProduct).get();
    }

    @Override
    public boolean isExistId(Long id) {
        return productRepository.existsById(id);
    }

    private boolean isAdmin(UserEntity user) {
        return user
                .getRoles()
                .stream()
                .map(RoleEntity::getRole)
                .anyMatch(r -> r == RoleNameEnum.ADMIN);
    }

    private ProductsViewModel mapDetailsProduct(ProductEntity productEntity) {

        return this.modelMapper.map(productEntity, ProductsViewModel.class);
    }
}
