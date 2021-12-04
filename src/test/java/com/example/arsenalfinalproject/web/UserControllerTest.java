package com.example.arsenalfinalproject.web;

import com.example.arsenalfinalproject.model.entity.RoleEntity;
import com.example.arsenalfinalproject.model.entity.UserEntity;
import com.example.arsenalfinalproject.model.entity.enums.RoleNameEnum;
import com.example.arsenalfinalproject.repository.RoleRepository;
import com.example.arsenalfinalproject.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    private static final String TEST_USER_EMAIL = "mario@ex.com";
    private static final String TEST_USERNAME = "MarioVl";
    private static final String TEST_FIRST_NAME = "Mario";
    private static final String TEST_LAST_NAME = "Vladimirov";

    @PostConstruct
    void setUp() {
        RoleEntity userRole = new RoleEntity();
        userRole.setRole(RoleNameEnum.USER);
        roleRepository.save(userRole);


        var testUser = new UserEntity();

        testUser.setPassword("12345");
        testUser.setUsername(TEST_USERNAME);
        testUser.setFirstName(TEST_FIRST_NAME);
        testUser.setLastName(TEST_LAST_NAME);
        testUser.setEmail(TEST_USER_EMAIL);
        testUser.setDateBirth(LocalDate.parse("2004-01-22"));
        testUser.setRoles(Set.of(userRole));

        testUser = userRepository.save(testUser);

    }


    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    void testOpenRegisterForm() throws Exception {
        mockMvc.
                perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }


    @Test
    void testRegisterUser() throws Exception {

        if (roleRepository.count() == 0) {
            RoleEntity userRole = new RoleEntity();
            userRole.setRole(RoleNameEnum.USER);
            roleRepository.save(userRole);

        }


        mockMvc.
                perform(post("/users/register")
                        .param("id", "1")
                        .param("username", TEST_USERNAME)
                        .param("firstName", TEST_FIRST_NAME)
                        .param("lastName", TEST_LAST_NAME)
                        .param("dateBirth", String.valueOf(LocalDate.now()))
                        .param("email", TEST_USER_EMAIL)
                        .param("password", "12345")
                        .param("confirmPassword", "12345")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))

                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(1, userRepository.count());

        Optional<UserEntity> newCreaterUserOp = userRepository.findByEmailIgnoreCase(TEST_USER_EMAIL);

        Assertions.assertTrue(newCreaterUserOp.isPresent());

        UserEntity userEntity = newCreaterUserOp.get();

        Assertions.assertEquals(TEST_USERNAME, userEntity.getUsername());
        Assertions.assertEquals(TEST_USER_EMAIL, userEntity.getEmail());
        Assertions.assertEquals(TEST_FIRST_NAME, userEntity.getFirstName());
        Assertions.assertEquals(TEST_LAST_NAME, userEntity.getLastName());

    }

    @Test
    void testRegisterUserInvalidField() throws Exception {
        mockMvc.
                perform(post("/users/register")
                        .param("id", "1")
                        .param("username", "")
                        .param("firstName", TEST_FIRST_NAME)
                        .param("lastName", TEST_LAST_NAME)
                        .param("dateBirth", String.valueOf(LocalDate.parse("2004-01-22")))
                        .param("email", TEST_USER_EMAIL)
                        .param("password", "12345")
                        .param("confirmPassword", "12345")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))

                .andExpect(status().is3xxRedirection());

        Optional<UserEntity> newUsername = userRepository.findByUsername("");

        assertTrue(newUsername.isEmpty());

    }

    @Test
    void testViewProfile() throws Exception {
        mockMvc
                .perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }


    @Test
    @WithUserDetails(value = "MarioVl")
    void testUpdateProfile() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/users/profile/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-user"));

    }



    @Test
    @WithUserDetails(value = "MarioVl")
    void testEditProfileDetailsError() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.patch("/users/profile/edit")
                .param("username", TEST_USERNAME)
                .param("firstName", "")
                .param("lastName", TEST_LAST_NAME)
                .param("dateBirth", String.valueOf(LocalDate.now()))
                .param("email", TEST_USER_EMAIL)
                .param("interest", "I love football and write code :) ")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(redirectedUrl("/users/profile/edit"));

    }

    @Test
    @WithUserDetails(value = TEST_USERNAME)
    void testEditProfileDetailsCorrect() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.patch("/users/profile/edit")
                .param("username", TEST_USERNAME)
                .param("firstName", "New first name")
                .param("lastName", TEST_LAST_NAME)
                .param("dateBirth", String.valueOf(LocalDate.now()))
                .param("email", TEST_USER_EMAIL)
                .param("interest", "I love football and write code :) ")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))

                .andExpect(status().is3xxRedirection());

        UserEntity userEntity = userRepository.findAll().get(0);

        Assertions.assertEquals("New first name" , userEntity.getFirstName());

    }

}