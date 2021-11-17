package com.example.arsenalfinalproject.service.impl;

import com.example.arsenalfinalproject.model.binding.ProductAddBindingModel;
import com.example.arsenalfinalproject.model.entity.ProductEntity;
import com.example.arsenalfinalproject.model.entity.RoleEntity;
import com.example.arsenalfinalproject.model.entity.UserEntity;
import com.example.arsenalfinalproject.model.entity.enums.RoleNameEnum;
import com.example.arsenalfinalproject.model.service.ProductAddServiceModel;
import com.example.arsenalfinalproject.model.service.ProductUpdateServiceModel;
import com.example.arsenalfinalproject.model.view.ProductsViewModel;
import com.example.arsenalfinalproject.repository.ProductRepository;
import com.example.arsenalfinalproject.service.ProductService;

import com.example.arsenalfinalproject.service.UserService;
import com.example.arsenalfinalproject.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;



    public ProductServiceImpl(ProductRepository productRepository, UserService userService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initializeProduct() {

        if (productRepository.count() == 0) {
            ProductEntity product1 = new ProductEntity();
            product1.setCountProduct(30);
            product1.setPrice(BigDecimal.valueOf(3));
            product1.setUrlPicture("https://res.cloudinary.com/mariovl/image/upload/v1635949788/shop/cards_lnzojy.jpg");
            product1.setProductName("Playing cards \"Arsenal Bulgaria");

            ProductEntity product2 = new ProductEntity();
            product2.setCountProduct(25);
            product2.setPrice(BigDecimal.valueOf(8));
            product2.setUrlPicture("https://res.cloudinary.com/mariovl/image/upload/v1635949789/shop/keyRing_uirpfo.jpg");
            product2.setProductName("Key ring \"15 years ASCB\"");

            ProductEntity product3 = new ProductEntity();
            product3.setCountProduct(65);
            product3.setPrice(BigDecimal.valueOf(10));
            product3.setUrlPicture("https://res.cloudinary.com/mariovl/image/upload/v1635949788/shop/siliconeBracelet_j321rx.jpg");
            product3.setProductName("Silicone bracelet \"Arsenal Bulgaria\"");

            ProductEntity product4 = new ProductEntity();
            product4.setCountProduct(15);
            product4.setPrice(BigDecimal.valueOf(35));
            product4.setUrlPicture("https://res.cloudinary.com/mariovl/image/upload/v1635949787/shop/backpack_ninmfv.jpg");
            product4.setProductName("Backpack \"Arsenal Bulgaria\"");

            ProductEntity product5 = new ProductEntity();
            product5.setCountProduct(25);
            product5.setPrice(BigDecimal.valueOf(29));
            product5.setUrlPicture("https://res.cloudinary.com/mariovl/image/upload/v1635949787/shop/tshirtPolo_cbmdym.jpg");
            product5.setProductName("Т-shirt \"Bulgarian Gooners\"");


            ProductEntity product6 = new ProductEntity();
            product6.setCountProduct(55);
            product6.setPrice(BigDecimal.valueOf(68));
            product6.setUrlPicture("https://res.cloudinary.com/mariovl/image/upload/v1635949786/shop/book_gpcr5u.jpg");
            product6.setProductName("Ian Wright - A Life In Football");

            ProductEntity product7 = new ProductEntity();
            product7.setCountProduct(23);
            product7.setPrice(BigDecimal.valueOf(6));
            product7.setUrlPicture("https://res.cloudinary.com/mariovl/image/upload/v1635949786/shop/glass_up1yxk.jpg");
            product7.setProductName("Beer glass \"Arsenal Bulgaria\" 420ml");

            ProductEntity product8 = new ProductEntity();
            product8.setCountProduct(42);
            product8.setPrice(BigDecimal.valueOf(8));
            product8.setUrlPicture("https://res.cloudinary.com/mariovl/image/upload/v1635949786/shop/pencil_f31naj.jpg");
            product8.setProductName("Pencil case \"Arsenal Bulgaria\"");


            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);
            productRepository.save(product4);
            productRepository.save(product5);
            productRepository.save(product6);
            productRepository.save(product7);
            productRepository.save(product8);

        }
    }

    @Override
    public List<ProductsViewModel> getAllProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductsViewModel.class))
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
                        new ObjectNotFoundException("Offer with id " + productUpdateServiceModel.getId() + " not found!"));

        productEntity.setProductName(productUpdateServiceModel.getProductName());
        productEntity.setCountProduct(productUpdateServiceModel.getCountProduct());
        productEntity.setPrice(productUpdateServiceModel.getPrice());
        productEntity.setUrlPicture(productUpdateServiceModel.getUrlPicture());

        productRepository.save(productEntity);
    }

    @Override
    public ProductAddServiceModel addOffer(ProductAddBindingModel productAddBindingModel) {

        ProductAddServiceModel productAddServiceModel =
                modelMapper.map(productAddBindingModel,ProductAddServiceModel.class);

        ProductEntity newProduct = modelMapper.map(productAddServiceModel,ProductEntity.class);

        ProductEntity savedProduct = productRepository.save(newProduct);

        return modelMapper.map(savedProduct,ProductAddServiceModel.class);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public boolean isOwner(String userName, Long id) {

        Optional<ProductEntity> productOpt = productRepository
                .findById(id);

        Optional<UserEntity> caller = userService.findByUsername(userName);

        if (productOpt.isEmpty() || caller.isEmpty()) {
            return false;
        }else  {
            ProductEntity productEntity = productOpt.get();

            return isAdmin(caller.get());
        }
    }

    private boolean isAdmin(UserEntity user) {
        return user
                .getRoles()
                .stream()
                .map(RoleEntity::getRole)
                .anyMatch(r -> r== RoleNameEnum.ADMIN);
    }

    private ProductsViewModel mapDetailsProduct(ProductEntity productEntity) {

        return this.modelMapper.map(productEntity, ProductsViewModel.class);
    }
}
