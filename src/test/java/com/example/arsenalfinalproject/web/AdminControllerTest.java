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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {


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
        RoleEntity adminTest = new RoleEntity();
        adminTest.setRole(RoleNameEnum.ADMIN);
        roleRepository.save(adminTest);

        RoleEntity userTest = new RoleEntity();
        userTest.setRole(RoleNameEnum.USER);
        roleRepository.save(userTest);





        var testAdmin = new UserEntity();

        testAdmin.setPassword("12345");
        testAdmin.setUsername(TEST_USERNAME);
        testAdmin.setFirstName(TEST_FIRST_NAME);
        testAdmin.setLastName(TEST_LAST_NAME);
        testAdmin.setEmail(TEST_USER_EMAIL);
        testAdmin.setDateBirth(LocalDate.parse("2004-01-22"));
        testAdmin.setRoles(Set.of(adminTest));

        testAdmin = userRepository.save(testAdmin);

        var testUser = new UserEntity();

        testUser.setPassword("12345");
        testUser.setUsername("User1");
        testUser.setFirstName("TEST_FIRST_NAME");
        testUser.setLastName("TEST_LAST_NAME");
        testUser.setEmail("user@ABV.BG");
        testUser.setDateBirth(LocalDate.parse("2004-01-22"));
        testUser.setRoles(Set.of(userTest));

        testUser = userRepository.save(testUser);


    }


    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    @WithUserDetails(value = TEST_USERNAME)
    void testAllUsersView() throws Exception {

        mockMvc
                .perform(get("/admin/adminchangeprofile"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminchangeprofile"));

    }

    @Test
    @WithUserDetails(value = TEST_USERNAME)
    void testEditRoleUserByAdmin() throws Exception {

        var user = userRepository.findByUsername("User1").get();

        RoleEntity moderatorTest = new RoleEntity();
        moderatorTest.setRole(RoleNameEnum.MODERATOR);
        roleRepository.save(moderatorTest);

        mockMvc
                .perform(MockMvcRequestBuilders.
                        patch("/admin/useredit/" + user.getId())
                        .param("username" , user.getUsername())
                        .param("role" , RoleNameEnum.MODERATOR.toString())
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        var actual = userRepository.
                findByUsername("User1").get().getRoles().stream().findFirst().get();


        Assertions.assertEquals(moderatorTest.getRole() , actual.getRole());


    }

//    Long id;
//    String username;
//    String role;


}