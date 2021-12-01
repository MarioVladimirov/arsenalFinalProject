package com.example.arsenalfinalproject.service.impl;


import com.example.arsenalfinalproject.model.entity.RoleEntity;
import com.example.arsenalfinalproject.model.entity.UserEntity;
import com.example.arsenalfinalproject.model.entity.enums.RoleNameEnum;
import com.example.arsenalfinalproject.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
 class ArsenalUserServiceImplTest {

    private ArsenalUserServiceImpl serviceToTest;

    private UserEntity testUser;

    private RoleEntity adminRole, userRole , moderatorRole;


    @Mock
    private UserRepository mockUserRepository;


    @BeforeEach
    void init () {

        serviceToTest = new ArsenalUserServiceImpl(mockUserRepository);

        adminRole = new RoleEntity();
        adminRole.setRole(RoleNameEnum.ADMIN);

        testUser = new UserEntity();
        testUser.setUsername("Mario");
        testUser.setEmail("mario@abv.bg");
        testUser.setPassword("12345");
        testUser.setRoles(Set.of(adminRole));

    }

        @Test
        void testUserNotFound() {
            Assertions.assertThrows(
                    UsernameNotFoundException.class,
                    () ->  serviceToTest.loadUserByUsername("invalid_user_email.com")
            );
        }

        @Test
        void testUserFound() {

            Mockito.when(mockUserRepository.findByUsername(testUser.getUsername())).
                    thenReturn(Optional.of(testUser));

            var actual =
                    serviceToTest.loadUserByUsername(testUser.getUsername());

            Assertions.assertEquals(actual.getUsername() , testUser.getUsername());

            String actualRoles = actual.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(", "));

            String expectedRoles = "ROLE_ADMIN";

            Assertions.assertEquals(actual.getUsername(),testUser.getUsername());
            Assertions.assertEquals(expectedRoles,actualRoles);
        }


}
