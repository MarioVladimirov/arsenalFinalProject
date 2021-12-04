package com.example.arsenalfinalproject.service.impl;

import com.example.arsenalfinalproject.model.entity.OrderEntity;
import com.example.arsenalfinalproject.model.entity.RoleEntity;
import com.example.arsenalfinalproject.model.entity.UserEntity;
import com.example.arsenalfinalproject.model.entity.enums.RoleNameEnum;
import com.example.arsenalfinalproject.model.service.UserChangeProfileServiceModel;
import com.example.arsenalfinalproject.model.service.UserEditServiceModel;
import com.example.arsenalfinalproject.model.service.UserRegisterServiceModel;
import com.example.arsenalfinalproject.model.view.OrderAllByOneUserViewModel;
import com.example.arsenalfinalproject.model.view.UserEditView;
import com.example.arsenalfinalproject.model.view.UserViewModel;
import com.example.arsenalfinalproject.repository.RoleRepository;
import com.example.arsenalfinalproject.repository.UserRepository;
import com.example.arsenalfinalproject.service.RoleService;
import com.example.arsenalfinalproject.service.UserService;
import com.example.arsenalfinalproject.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ArsenalUserServiceImpl arsenalUserService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ArsenalUserServiceImpl arsenalUserService, RoleService roleService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.arsenalUserService = arsenalUserService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    @Override
    public Optional<UserEntity> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserEntity> findByUsername(String userIdentifier) {
        return userRepository.findByUsername(userIdentifier);
    }




    private void initializeUsers() {
        if (userRepository.count() == 0) {
            RoleEntity adminRole = roleRepository.findByRole(RoleNameEnum.ADMIN);
            RoleEntity moderatorRole = roleRepository.findByRole(RoleNameEnum.MODERATOR);
            RoleEntity userRole = roleRepository.findByRole(RoleNameEnum.USER);

            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("12345"));
            admin.setEmail("admin@arsenal-bulgaria.com");
            admin.setDateBirth(LocalDate.parse("2004-01-22"));
            admin.setFirstName("Petar");
            admin.setLastName("Petrov");
            admin.setRoles(Set.of(adminRole));
            userRepository.save(admin);


            UserEntity moderator = new UserEntity();

            moderator.setUsername("Ivan87");
            moderator.setPassword(passwordEncoder.encode("12345"));
            moderator.setEmail("ivan87@abv.bg");
            moderator.setDateBirth(LocalDate.parse("1987-01-22"));
            moderator.setFirstName("Ivan");
            moderator.setLastName("Ivanov");
            moderator.setRoles(Set.of(moderatorRole));
            userRepository.save(moderator);

            UserEntity user = new UserEntity();

            user.setUsername("user1");
            user.setPassword(passwordEncoder.encode("12345"));
            user.setEmail("waser@abv.bg");
            user.setDateBirth(LocalDate.parse("1986-06-15"));
            user.setFirstName("Mario");
            user.setLastName("Vladimirov");
            user.setRoles(Set.of(userRole));
            userRepository.save(user);

        }
    }

    private void initializeRoles() {
        if (roleRepository.count() == 0) {
            RoleEntity adminRole = new RoleEntity();
            adminRole.setRole(RoleNameEnum.ADMIN);

            RoleEntity moderatorRole = new RoleEntity();
            moderatorRole.setRole(RoleNameEnum.MODERATOR);

            RoleEntity userRole = new RoleEntity();
            userRole.setRole(RoleNameEnum.USER);

            roleRepository.saveAll(List.of(adminRole, moderatorRole, userRole));
        }


    }


    @Override
    public void registerUserAndLogin(UserRegisterServiceModel userRegisterServiceModel) {

        RoleEntity roleUser = roleRepository.findByRole(RoleNameEnum.USER);

        UserEntity newUser = new UserEntity();

        newUser.setUsername(userRegisterServiceModel.getUsername());
        newUser.setFirstName(userRegisterServiceModel.getFirstName());
        newUser.setLastName(userRegisterServiceModel.getLastName());
        newUser.setEmail(userRegisterServiceModel.getEmail());
        newUser.setDateBirth(userRegisterServiceModel.getDateBirth());
        newUser.setPassword(passwordEncoder.encode(userRegisterServiceModel.getPassword()));
        newUser.setRoles(Set.of(roleUser));

        newUser = userRepository.save(newUser);

        // Registration in Spring and login user auto

        UserDetails principal = arsenalUserService.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);

    }

    @Override
    public boolean isUserNameFree(String userName) {
        return userRepository.findByUsernameIgnoreCase(userName).isEmpty();
    }

    @Override
    public boolean isEmailFree(String email) {
        return userRepository.findByEmailIgnoreCase(email).isEmpty();
    }


    @Override
    public void editUserRole(Long idUser, String role) {


    }

    @Override
    public String getUsernameById(Long id) {
        return userRepository.getById(id).getUsername();
    }

    @Override
    public void changeProfileRole(UserChangeProfileServiceModel serviceModel) {
        UserEntity userEntity =
                userRepository.findById(serviceModel.getId()).orElseThrow(() ->
                        new ObjectNotFoundException(serviceModel.getId()));


        Set<RoleEntity> roleEntity = roleService.getRoleByName(serviceModel.getRole());

        userEntity.setRoles(roleEntity);

        userRepository.save(userEntity);

    }
    @Override
    public List<UserViewModel> getAllUsers() {

        List<UserEntity> userEntity = userRepository.findAll();

        return getUserViewModels(userEntity);
    }




    @Override
    public List<UserViewModel> findByKeyword(String keyword) {

        List<UserEntity> byKeyword = userRepository.findByKeyword(keyword);

        return getUserViewModels(byKeyword);


    }

    @Override
    public UserEditView findByUsernameViewModel(String currentName) {

        return modelMapper.map(userRepository.findByUsername(currentName).get()
                ,UserEditView.class);
    }
    @Transactional
    @Override
    public void updateUserProfile(UserEditServiceModel userEditServiceModel , String currentUser) {
        UserEntity userEntity = userRepository.findByUsername(currentUser).get();


            userEntity.setFirstName(userEditServiceModel.getFirstName());
            userEntity.setLastName(userEditServiceModel.getLastName());
            userEntity.setEmail(userEditServiceModel.getEmail());
            userEntity.setDateBirth(userEditServiceModel.getDateBirth());
            userEntity.setDescription(userEditServiceModel.getDescription());
            userEntity.setFavoritePlayer(userEditServiceModel.getFavoritePlayer());
            userEntity.setInterest(userEditServiceModel.getInterest());
            userEntity.setLoveTrip(userEditServiceModel.getLoveTrip());


            userRepository.save(userEntity);
    }

    @Override
    public boolean isExistId(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserEntity getUserEntityById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public boolean isAdmin(String name) {
        UserEntity userEntity = userRepository.findByUsername(name).get();
        return
                userEntity
                        .getRoles()
                        .stream()
                        .map(RoleEntity::getRole)
                        .anyMatch(r -> r== RoleNameEnum.ADMIN);
    }

//    @Override
//    public List<OrderAllByOneUserViewModel> findAllOrderForOneUserByCurrentUser(String currentUser) {
//        UserEntity userEntity = userRepository.findByUsername(currentUser).get();
//
//        List<OrderEntity> allOrder = userEntity.getAllOrder();
//
//
//
//        return allOrder
//                    .stream()
//                    .map(product -> {
//                        OrderAllByOneUserViewModel currentProduct = new OrderAllByOneUserViewModel();
//                        currentProduct
//                                .setUrlPicture(product.getProduct().getPicture().getUrl())
//                                .setName(product.getName())
//                                .setDateByOrder(product.getDateByOrder())
//                                .setTotalSum(product.getTotalSum())
//                                .setName(product.getUser().getFirstName() + " " + product.getUser().getLastName());
//                        return currentProduct;
//                    })
//                .collect(Collectors.toList());
//    }

    private List<UserViewModel> getUserViewModels(List<UserEntity> userEntity) {
        return
                userEntity.stream()
                        .map(userEntity1 -> {
                            UserViewModel userViewModel = modelMapper.map(userEntity1,UserViewModel.class);

                            Set<RoleEntity> roles = userEntity1.getRoles();
                            RoleEntity roleEntity = roles.stream().findFirst().get();
                            String role = roleEntity.getRole().toString();

                            userViewModel.setRole(role);

                            return userViewModel;
                        })
                        .collect(Collectors.toList());
    }


}
