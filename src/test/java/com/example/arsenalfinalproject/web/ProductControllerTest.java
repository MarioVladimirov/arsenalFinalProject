package com.example.arsenalfinalproject.web;

import com.example.arsenalfinalproject.model.entity.PictureEntity;
import com.example.arsenalfinalproject.model.entity.ProductEntity;
import com.example.arsenalfinalproject.model.entity.RoleEntity;
import com.example.arsenalfinalproject.model.entity.UserEntity;
import com.example.arsenalfinalproject.model.entity.enums.RoleNameEnum;
import com.example.arsenalfinalproject.repository.PictureRepository;
import com.example.arsenalfinalproject.repository.ProductRepository;
import com.example.arsenalfinalproject.repository.RoleRepository;
import com.example.arsenalfinalproject.repository.UserRepository;
import com.example.arsenalfinalproject.service.ProductService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.io.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private PictureRepository pictureRepository;

    private static final String TEST_USER_EMAIL = "mario@ex.com";
    private static final String TEST_USERNAME = "MarioVl";
    private static final String TEST_FIRST_NAME = "Mario";
    private static final String TEST_LAST_NAME = "Vladimirov";

    @PostConstruct
    void setUp() {
        RoleEntity adminTest = new RoleEntity();
        adminTest.setRole(RoleNameEnum.ADMIN);
        roleRepository.save(adminTest);

        var testUser = new UserEntity();

        testUser.setPassword("12345");
        testUser.setUsername(TEST_USERNAME);
        testUser.setFirstName(TEST_FIRST_NAME);
        testUser.setLastName(TEST_LAST_NAME);
        testUser.setEmail(TEST_USER_EMAIL);
        testUser.setDateBirth(LocalDate.parse("2004-01-22"));
        testUser.setRoles(Set.of(adminTest));

        testUser = userRepository.save(testUser);

    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
        pictureRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    void testProductPageAll() throws Exception {
        mockMvc
                .perform(get("/product/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("product"));
    }

    @Test
    @WithUserDetails(value = "MarioVl")
    void testProductPageAllWith() throws Exception {
        mockMvc
                .perform(get("/product/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("product"));
    }

    @Test
    @WithUserDetails(value = "MarioVl")
    void testGetAddOfferPageCorrect() throws Exception {

        mockMvc
                .perform(get("/product/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("addproduct"));

    }

    @Test
    @WithUserDetails(value = "MarioVl")
    void testAddProductError() throws Exception {


        mockMvc
                .perform(post("/product/add")
                        .param("picture", "picture")
                        .param("productName", "Product first")
                        .param("price", "1")
                        .param("countProduct", "2")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(redirectedUrl("/product/add"));


    }


    @Test
    @WithUserDetails(value = "MarioVl")
    void testEditProductView() throws Exception {
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setPublicId("ssssssss");
        pictureEntity.setUrl("asdasdasdas");
        pictureRepository.save(pictureEntity);


        ProductEntity testProduct = new ProductEntity();
        testProduct.setPicture(pictureRepository.findAll().get(0));
        testProduct.setCountProduct(30);
        testProduct.setProductName("test product");
        testProduct.setPrice(BigDecimal.valueOf(3.4));


        productRepository.save(testProduct);

        mockMvc
                .perform(get("/product/" + testProduct.getId() + "/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("editproduct"));
    }

    @Test
    @WithUserDetails(value = TEST_USERNAME)
    void testEditProduct() throws Exception {
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setPublicId("ssssssss");
        pictureEntity.setUrl("asdasdasdas");

        pictureRepository.save(pictureEntity);


        ProductEntity testProduct = new ProductEntity();
        testProduct.setPicture(pictureRepository.findAll().get(0));
        testProduct.setCountProduct(30);
        testProduct.setProductName("test product");
        testProduct.setPrice(BigDecimal.valueOf(3.4));


        productRepository.save(testProduct);


        mockMvc
                .perform(MockMvcRequestBuilders.patch("/product/" + testProduct.getId() + "/edit")
                        .param("id", "1")
                        .param("urlPicture", "asdasdasdas")
                        .param("productName", "new name of product")
                        .param("price", "3.4")
                        .param("countProduct", "30")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals("new name of product" , productRepository.findAll().get(0).getProductName());


    }

    @Test
    @WithUserDetails(value = TEST_USERNAME)
    void testEditProductError() throws Exception {
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setPublicId("ssssssss");
        pictureEntity.setUrl("asdasdasdas");

        pictureRepository.save(pictureEntity);


        ProductEntity testProduct = new ProductEntity();
        testProduct.setPicture(pictureRepository.findAll().get(0));
        testProduct.setCountProduct(30);
        testProduct.setProductName("test product");
        testProduct.setPrice(BigDecimal.valueOf(3.4));


        productRepository.save(testProduct);


        mockMvc
                .perform(MockMvcRequestBuilders.patch("/product/" + testProduct.getId() + "/edit")
                        .param("id", "1LLL")
                        .param("urlPicture", "asdasdasdas")
                        .param("productName", "new name of product")
                        .param("price", "3.4")
                        .param("countProduct", "30")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(redirectedUrl("/" + testProduct.getId() + "/edit/errors"));

    }

//    @Test
//    @WithUserDetails(value = TEST_USERNAME)
//    void testDeleteProduct() throws Exception {
//        PictureEntity pictureEntity = new PictureEntity();
//        pictureEntity.setPublicId("ssssssss");
//        pictureEntity.setUrl("asdasdasdas");
//        pictureEntity.setId(1L);
//        pictureRepository.save(pictureEntity);
//
//
//        ProductEntity testProduct = new ProductEntity();
//        testProduct.setPicture(pictureRepository.findById(1L).get());
//        testProduct.setCountProduct(30);
//        testProduct.setProductName("test product");
//        testProduct.setPrice(BigDecimal.valueOf(3.4));
//        testProduct.setId(1L);
//
//        productRepository.save(testProduct);
//
//        mockMvc
//                .perform(delete("/product/" + testProduct.getId())
//                        .param("public_id" , "ssssssss")
//                        .with(csrf()))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/product/all"));
//
//       Assertions.assertEquals(0, productRepository.count());
//
//    }












//    @Test
//    @WithUserDetails(value = "MarioVl")
//    void testAddProductCorrect() throws Exception {
//
//        MockMultipartFile log = new MockMultipartFile("file", "filename.txt", "application/octet-stream",
//                "some xml".getBytes());
//
//        MockMultipartFile image2 = new MockMultipartFile("logo", " logo","image/jpeg" , "src/main/resources/static/images/logo.png".getBytes() );
//        mockMvc
//                .perform(MockMvcRequestBuilders.multipart("/product/add")
//                        .file("picture" , log.getBytes())
//                        .param("productName" , "Product name")
//                        .param("price" , "1")
//                        .param("countProduct" , "2")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED));
//
//        Optional<ProductEntity> currentSaveProduct = productRepository.findById(1L);
//
//        Assertions.assertTrue(currentSaveProduct.isPresent());
//        Assertions.assertEquals("Product name" , currentSaveProduct.get().getProductName());
//
//    }


//    MultipartFile picture;
//    String productName;
//    BigDecimal price;
//    Integer countProduct;


}