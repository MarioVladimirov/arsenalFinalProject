package com.example.arsenalfinalproject.web;

import com.example.arsenalfinalproject.model.entity.NewsEntity;
import com.example.arsenalfinalproject.model.entity.PictureEntity;
import com.example.arsenalfinalproject.model.entity.RoleEntity;
import com.example.arsenalfinalproject.model.entity.UserEntity;
import com.example.arsenalfinalproject.model.entity.enums.RoleNameEnum;
import com.example.arsenalfinalproject.repository.NewsRepository;
import com.example.arsenalfinalproject.repository.PictureRepository;
import com.example.arsenalfinalproject.repository.RoleRepository;
import com.example.arsenalfinalproject.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private NewsRepository newsRepository;

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

        testUser = this.userRepository.save(testUser);


        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setPublicId("ssssssss");
        pictureEntity.setUrl("picture url...");

        pictureRepository.save(pictureEntity);

        NewsEntity testNews = new NewsEntity();
        testNews.setPicture(pictureEntity);
        testNews.setDescription("Description of news ....");
        testNews.setLocalDateNews(LocalDate.now());
        testNews.setTopic("topci is news or ...");

        testNews.setUser(testUser);



        newsRepository.save(testNews);


    }

    @AfterEach
    void tearDown() {
        newsRepository.deleteAll();
        pictureRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }


    @Test
    @WithUserDetails(TEST_USERNAME)
    void testHomeView() throws Exception {

        mockMvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));



    }




}