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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class NewsControllerTest {


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
        List<NewsEntity> all = newsRepository.findAll();
        List<UserEntity> all1 = userRepository.findAll();
        List<PictureEntity> all2 = pictureRepository.findAll();



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

        List<PictureEntity> all2Two = pictureRepository.findAll();
        List<UserEntity> all1Two = userRepository.findAll();

        newsRepository.save(testNews);


        List<NewsEntity> allTwo = newsRepository.findAll();

    }

    @AfterEach
    void tearDown() {
        newsRepository.deleteAll();
        pictureRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }



    @Test
    @WithUserDetails(value = TEST_USERNAME)
    void testShowNews() throws Exception {

        NewsEntity newsEntity = newsRepository.findAll().get(0);

        mockMvc
             .perform(get("/news/details/" + newsEntity.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("details-news"));

    }

    @Test
    @WithUserDetails(value = TEST_USERNAME)
    void testUpdateNewsView() throws Exception {

        NewsEntity newsEntity = newsRepository.findAll().get(0);

        mockMvc
                .perform(get("/news/edit/" + newsEntity.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("editnews"));


    }

    @Test
    @WithUserDetails(value = TEST_USERNAME)
    void testUpdateNewsCorrect() throws Exception {

        NewsEntity newsEntity = newsRepository.findAll().get(0);

        mockMvc
                .perform(MockMvcRequestBuilders.patch("/news/edit/" + newsEntity.getId())
                        .param("id" , "1")
                        .param("urlPictureNews" ,"picture url..." )
                        .param("Topic" , "new topic")
                        .param("description" , " description new ... ")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        var actual = newsRepository.findAll().get(0).getTopic();

        Assertions.assertEquals("new topic" , actual);

    }
    @Test
    @WithUserDetails(value = TEST_USERNAME)
    void testUpdateNewsError() throws Exception {

        NewsEntity newsEntity = newsRepository.findAll().get(0);

        mockMvc
                .perform(MockMvcRequestBuilders.patch("/news/edit/" + newsEntity.getId())
                        .param("id" , "1211s")
                        .param("urlPictureNews" ,"picture url..." )
                        .param("Topic" , "new topic")
                        .param("description" , " description new ... ")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(redirectedUrl("/"+ newsEntity.getId() + "/edit/errors"));


    }

    @Test
    @WithUserDetails(value = TEST_USERNAME)
    void testGetNewsPageCorrectView() throws Exception {

        mockMvc.perform(get("/news/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("addnews"));


    }

    @Test
    @WithUserDetails(value = TEST_USERNAME)
    void testAddNewsErrorData() throws Exception {


        mockMvc
                .perform(post("/news/add")
                        .param("picture" , "1DD")
                        .param("Topic" ,"" )
                        .param("description" , " description new ... ")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(redirectedUrl("/news/add"));

    }





}