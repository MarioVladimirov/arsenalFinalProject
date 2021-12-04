package com.example.arsenalfinalproject.web;

import com.example.arsenalfinalproject.helper.Message;
import com.example.arsenalfinalproject.model.binding.ProductUpdateBindingModel;
import com.example.arsenalfinalproject.model.binding.UserEditBindingModel;
import com.example.arsenalfinalproject.model.binding.UserRegisterBindingModel;
import com.example.arsenalfinalproject.model.entity.UserEntity;
import com.example.arsenalfinalproject.model.service.UserEditServiceModel;
import com.example.arsenalfinalproject.model.service.UserRegisterServiceModel;
import com.example.arsenalfinalproject.model.view.UserEditView;
import com.example.arsenalfinalproject.service.UserService;
import com.example.arsenalfinalproject.service.impl.ArsenalUser;
import com.example.arsenalfinalproject.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userRegisterBindingModel")
    public UserRegisterBindingModel userModel() {
        return new UserRegisterBindingModel();
    }

    @GetMapping("/register")
    public String register() {


        return "register";
    }


    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors() ||
                !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        UserRegisterServiceModel userRegisterServiceModel =
                modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class);

        userService.registerUserAndLogin(userRegisterServiceModel);

        redirectAttributes.addFlashAttribute("registerSuccess",
                "Your registration was successful2!");


        return "redirect:/";
    }


    @GetMapping("/login")
    public String login() {

        return "login";
    }

    //When we have error login and return error
    @PostMapping("/login-error")
    public String failedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                    String userName,
            RedirectAttributes attributes
    ) {

        attributes.addFlashAttribute("bad_credentials", true);
        attributes.addFlashAttribute("username", userName);

        return "redirect:/users/login";
    }

    @GetMapping("/profile/edit")
    public String viewProfile(@AuthenticationPrincipal ArsenalUser currentUser, Model model) {


        UserEditView byUsernameViewModel = userService.findByUsernameViewModel(currentUser.getUserIdentifier());

        UserEditBindingModel userEditBindingModel = modelMapper.map(byUsernameViewModel,
                UserEditBindingModel.class);

        model.addAttribute("userEditModel", userEditBindingModel);

        return "edit-user";

    }

    @PatchMapping("/profile/edit")
    public String editProfile(@AuthenticationPrincipal ArsenalUser currentUser,
                              @Valid UserEditBindingModel userEditBindingModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userEditBindingModel", userEditBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userEditBindingModel",
                    bindingResult);

            return "redirect:/users/profile/edit";

        }

        UserEditServiceModel userEditServiceModel = modelMapper.map(userEditBindingModel, UserEditServiceModel.class);

        userService.updateUserProfile(userEditServiceModel, currentUser.getUserIdentifier());


        redirectAttributes.addFlashAttribute("updateProfile",
                "You have successfully made changes to your account ");

        return "redirect:/";
    }


}
