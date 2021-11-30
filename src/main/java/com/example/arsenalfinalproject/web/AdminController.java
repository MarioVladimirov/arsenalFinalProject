package com.example.arsenalfinalproject.web;
import com.example.arsenalfinalproject.model.binding.UserChangeProfileBindingModel;
import com.example.arsenalfinalproject.model.service.UserChangeProfileServiceModel;
import com.example.arsenalfinalproject.service.RoleService;
import com.example.arsenalfinalproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    public AdminController(UserService userService, RoleService roleService, ModelMapper modelMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/adminchangeprofile")
    public String allUsers(Model model , String keyword) {


        if (keyword != null) {
            model.addAttribute("allusers",userService.findByKeyword(keyword));
        }else {

        model.addAttribute("allusers", userService.getAllUsers());
        }

        model.addAttribute("allrole", roleService.getAllRole());

        return "adminchangeprofile";
    }


    @PatchMapping("/useredit/{id}")
    public String editRoleUserByAdmin(@PathVariable Long id,
                                      @Valid UserChangeProfileBindingModel userChangeProfileBindingModel) {


        UserChangeProfileServiceModel userChangeProfileServiceModel =
                modelMapper.map(userChangeProfileBindingModel, UserChangeProfileServiceModel.class);

        userService.changeProfileRole(userChangeProfileServiceModel);


        return "redirect:/admin/adminchangeprofile";
    }


}
