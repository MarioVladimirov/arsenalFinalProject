package com.example.arsenalfinalproject.web;

import com.example.arsenalfinalproject.helper.Message;
import com.example.arsenalfinalproject.model.binding.UserRegisterBindingModel;
import com.example.arsenalfinalproject.model.service.UserRegisterServiceModel;
import com.example.arsenalfinalproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
                                  RedirectAttributes redirectAttributes ,
                                  HttpSession httpSession) {

        System.out.println();

        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        UserRegisterServiceModel userRegisterServiceModel =
                modelMapper.map(userRegisterBindingModel , UserRegisterServiceModel.class);

        userService.registerUser(userRegisterServiceModel);

        httpSession.setAttribute("message" , new Message("Your registration was successful!","success"));



        return "redirect:/";
    }


    @GetMapping("/login")
    public String login() {

        return "login";
    }


}
