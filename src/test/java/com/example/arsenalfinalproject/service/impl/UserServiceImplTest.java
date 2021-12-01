package com.example.arsenalfinalproject.service.impl;

import com.example.arsenalfinalproject.model.entity.ProductEntity;
import com.example.arsenalfinalproject.model.entity.RoleEntity;
import com.example.arsenalfinalproject.model.entity.UserEntity;
import com.example.arsenalfinalproject.model.entity.enums.RoleNameEnum;
import com.example.arsenalfinalproject.model.service.ProductUpdateServiceModel;
import com.example.arsenalfinalproject.model.service.UserChangeProfileServiceModel;
import com.example.arsenalfinalproject.model.service.UserRegisterServiceModel;
import com.example.arsenalfinalproject.repository.RoleRepository;
import com.example.arsenalfinalproject.repository.UserRepository;
import com.example.arsenalfinalproject.service.RoleService;
import com.example.arsenalfinalproject.web.exception.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private UserServiceImpl userServiceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ArsenalUserServiceImpl arsenalUserService;

    @Mock
    private RoleService roleService;

    private final ModelMapper modelMapper = new ModelMapper();
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserEntity testUser;

    private RoleEntity adminRole, userRole, moderatorRole;

    @BeforeEach
    void init() {
        userServiceToTest = new UserServiceImpl(mockUserRepository, roleRepository,
                arsenalUserService, roleService, modelMapper, passwordEncoder);

        adminRole = new RoleEntity();
        adminRole.setRole(RoleNameEnum.ADMIN);

        testUser = new UserEntity();
        testUser.setId(1L);
        testUser.setUsername("Mario");
        testUser.setEmail("mario@abv.bg");
        testUser.setPassword("12345");
        testUser.setRoles(Set.of(adminRole));

    }

    @Test
    void testFindUserId() {

     Mockito.when(mockUserRepository.findById(testUser.getId()))
             .thenReturn(Optional.of(testUser));

        var actual =
                userServiceToTest.findUserById(1L);

        Assertions.assertEquals(testUser.getUsername(),actual.get().getUsername());

    }

    @Test
    void testFindByUsername() {
        Mockito.when(mockUserRepository.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));

        var actual =
                userServiceToTest.findByUsername("Mario");


        Assertions.assertEquals(testUser.getUsername(),actual.get().getUsername());
        Assertions.assertEquals(testUser.getDateBirth(),actual.get().getDateBirth());

    }

    @Test
    void testIsUserNameFreeUnique() {

        Mockito.when(mockUserRepository.findByUsernameIgnoreCase(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));

        var actual = userServiceToTest.isUserNameFree(testUser.getUsername());

        Assertions.assertFalse(actual);

        var actual2 = userServiceToTest.isUserNameFree("mmmmm");
        Assertions.assertTrue(actual2);
    }


    @Test
    void testIsEmailFree() {
        Mockito.when(mockUserRepository.findByEmailIgnoreCase(testUser.getEmail()))
                .thenReturn(Optional.of(testUser));

        var actual = userServiceToTest.isEmailFree(testUser.getEmail());

        Assertions.assertFalse(actual);


        var actual2 = userServiceToTest.isEmailFree("rrr@Ap.com");

        Assertions.assertTrue(actual2);

    }

    @Test
    void testGetUsernameById() {
        Mockito.when(mockUserRepository.getById(testUser.getId()))
            .thenReturn(Optional.of(testUser).get());

        var actual = userServiceToTest.getUsernameById(1L);

        Assertions.assertEquals(testUser.getUsername(),actual);

    }

    @Test
    void testChangeProfileRole() {
        UserChangeProfileServiceModel testChangeProfileRole = new UserChangeProfileServiceModel();
        testChangeProfileRole.setRole(RoleNameEnum.USER.name());
        testChangeProfileRole.setUsername("Mario");
        testChangeProfileRole.setId(1L);

        Mockito.when(mockUserRepository.findById(testUser.getId()))
                .thenReturn(Optional.of(testUser));


         userServiceToTest.changeProfileRole(testChangeProfileRole);

        Assertions.assertEquals(testUser.getRoles().stream().findFirst(),
                userServiceToTest.findUserById(1L).get().getRoles().stream().findFirst());


    }

    @Test
    void testChangeProfileRoleExc() {
        UserChangeProfileServiceModel testChangeProfileRole = new UserChangeProfileServiceModel();
        testChangeProfileRole.setRole(RoleNameEnum.USER.name());
        testChangeProfileRole.setUsername("Mario");
        testChangeProfileRole.setId(1L);

        Assertions.assertThrows(ObjectNotFoundException.class ,
                () -> userServiceToTest.changeProfileRole(testChangeProfileRole));


    }

    @Test
    void testGetAllUsers() {
        List<UserEntity> listTestUser = new ArrayList<>();
        listTestUser.add(testUser);

        Mockito.when(mockUserRepository.findAll()).thenReturn(listTestUser);

        var actual = userServiceToTest.getAllUsers();

        Assertions.assertEquals(listTestUser.get(0).getUsername() ,
                actual.get(0).getUsername());

    }

    @Test
    void testFindByKeyword() {
        List<UserEntity> listTestUser = new ArrayList<>();
        listTestUser.add(testUser);

        Mockito.when(mockUserRepository.findByKeyword("Mar"))
                .thenReturn(listTestUser);

        var actual = userServiceToTest.findByKeyword("Mar");

        Assertions.assertEquals(listTestUser.get(0).getUsername() ,
                actual.get(0).getUsername());


    }




}
