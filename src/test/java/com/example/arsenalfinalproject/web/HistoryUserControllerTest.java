package com.example.arsenalfinalproject.web;

import com.example.arsenalfinalproject.model.entity.RoleEntity;
import com.example.arsenalfinalproject.model.entity.UserEntity;
import com.example.arsenalfinalproject.model.entity.enums.RoleNameEnum;
import com.example.arsenalfinalproject.repository.MemberTopicRepository;
import com.example.arsenalfinalproject.repository.PictureRepository;
import com.example.arsenalfinalproject.repository.RoleRepository;
import com.example.arsenalfinalproject.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import  org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class HistoryUserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MemberTopicRepository memberTopicRepository;


    @Autowired
    private PictureRepository pictureRepository;

    private static final String TEST_USER_EMAIL = "mario@ex.com";
    private static final String TEST_USERNAME = "MarioVl";
    private static final String TEST_FIRST_NAME = "Mario";
    private static final String TEST_LAST_NAME = "Vladimirov";

    @PostConstruct
    void setUp() {
        RoleEntity userTest = new RoleEntity();
        userTest.setRole(RoleNameEnum.USER);
        roleRepository.save(userTest);

        var testUser = new UserEntity();

        testUser.setPassword("12345");
        testUser.setUsername(TEST_USERNAME);
        testUser.setFirstName(TEST_FIRST_NAME);
        testUser.setLastName(TEST_LAST_NAME);
        testUser.setEmail(TEST_USER_EMAIL);
        testUser.setDateBirth(LocalDate.parse("2004-01-22"));
        testUser.setRoles(Set.of(userTest));

        testUser = userRepository.save(testUser);

    }
    @AfterEach
    void tearDown() {
        memberTopicRepository.deleteAll();
        pictureRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }


    @Test
    @WithUserDetails(value = TEST_USERNAME)
    void testViewHistoryAdd() throws Exception {

        mockMvc
                .perform(get("/api/historyuser/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-historyfans"));


    }

    @Test
    @WithUserDetails(value = TEST_USERNAME)
    void addHistoryError() throws Exception {

        mockMvc
                .perform(post("/api/historyuser/add")
                        .param("title" , "d")
                        .param("description" , "aaaERTbml")
                        .param("picture" , "d")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(redirectedUrl("/api/historyuser/add"));



    }



}