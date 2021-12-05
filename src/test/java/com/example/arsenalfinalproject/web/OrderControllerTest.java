package com.example.arsenalfinalproject.web;

import com.example.arsenalfinalproject.model.entity.*;
import com.example.arsenalfinalproject.model.entity.enums.RoleNameEnum;
import com.example.arsenalfinalproject.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberTopicRepository memberTopicRepository;


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
        memberTopicRepository.deleteAll();
        pictureRepository.deleteAll();
        orderRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }


    @Test
    @WithUserDetails(value = TEST_USERNAME)
    void testViewOrderCorrect() throws Exception {
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
        Long idOfProduct = productRepository.findAll().get(0).getId();

        mockMvc
                .perform(get("/order/" + idOfProduct))
                .andExpect(status().isOk())
                .andExpect(view().name("order"));


    }

    @Test
    @WithUserDetails(TEST_USERNAME)
    void testAllOrderByUserCorrect() throws Exception {

        mockMvc
                .perform(get("/order/allorderbyuser"))
                .andExpect(status().isOk())
                .andExpect(view().name("purchase"));


    }

    @Test
    @WithUserDetails(TEST_USERNAME)
    void testNewOrderCorrect() throws Exception {
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

        Long idOfProduct = productRepository.findAll().get(0).getId();


        mockMvc
                .perform(post("/order/" + idOfProduct)
                        .param("address", "Buxton ....")
                        .param("mobilePhone" , "33333333")
                        .param("name" , "Mario Vladimirov")
                        .param("description" , "I want my product ....")
                        .param("urlPicture" , "asdasdasdas")
                        .param("price" , "3.4")
                        .param("productName" , "test product")
                        .param("count" , "2")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(redirectedUrl("/"));


            var actual = productRepository.findAll().get(0).getCountProduct();

        Assertions.assertEquals(28 , actual );


    }
    @Test
    @WithUserDetails(TEST_USERNAME)
    void testNewOrderError() throws Exception {
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

        Long idOfProduct = productRepository.findAll().get(0).getId();


        mockMvc
                .perform(post("/order/" + idOfProduct)
                        .param("address", "Buxton ....")
                        .param("mobilePhone" , "33333333")
                        .param("name" , "Mario Vladimirov")
                        .param("description" , "I want my product ....")
                        .param("urlPicture" , "asdasdasdas")
                        .param("price" , "3.4")
                        .param("productName" , "test product")
                        .param("count" , "6666")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(redirectedUrl("/order/" + idOfProduct));

    }

    @Test
    @WithUserDetails(TEST_USERNAME)
    void testBuyMembership() throws Exception {
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setPublicId("ssssssss");
        pictureEntity.setUrl("asdasdasdas");
        pictureRepository.save(pictureEntity);

        UserEntity userEntityTest = userRepository.findAll().get(0);

        MemberTopicEntity memberOne = new MemberTopicEntity();
        memberOne.setPicture(pictureEntity);
        memberOne.setApproved(true);
        memberOne.setUser(userEntityTest);
        memberOne.setTitle("Title.....");
        memberOne.setDescription("Description on picture ....");
        memberTopicRepository.save(memberOne);

        PictureEntity pictureEntity1 = new PictureEntity();
        pictureEntity1.setPublicId("ssssssss");
        pictureEntity1.setUrl("asdasdasdas");
        pictureRepository.save(pictureEntity1);



        MemberTopicEntity memberTwo = new MemberTopicEntity();
        memberTwo.setPicture(pictureEntity1);
        memberTwo.setApproved(true);
        memberTwo.setUser(userEntityTest);
        memberTwo.setTitle("Title.....");
        memberTwo.setDescription("Description on picture ....");
        memberTopicRepository.save(memberTwo);


        PictureEntity pictureEntity2 = new PictureEntity();
        pictureEntity2.setPublicId("ssssssss");
        pictureEntity2.setUrl("asdasdasdas");
        pictureRepository.save(pictureEntity2);

        MemberTopicEntity memberThree = new MemberTopicEntity();
        memberThree.setPicture(pictureEntity);
        memberThree.setApproved(true);
        memberThree.setUser(userEntityTest);
        memberThree.setTitle("Title.....");
        memberThree.setDescription("Description on picture ....");
        memberTopicRepository.save(memberThree);

        mockMvc
                .perform(get("/order/membership"))
                .andExpect(status().isOk())
                .andExpect(view().name("membership"));


    }




}
