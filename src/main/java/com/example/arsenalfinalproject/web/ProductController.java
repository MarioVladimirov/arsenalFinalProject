package com.example.arsenalfinalproject.web;

import com.example.arsenalfinalproject.model.binding.ProductAddBindingModel;
import com.example.arsenalfinalproject.model.binding.ProductUpdateBindingModel;
import com.example.arsenalfinalproject.model.entity.RoleEntity;
import com.example.arsenalfinalproject.model.entity.enums.RoleNameEnum;
import com.example.arsenalfinalproject.model.service.ProductUpdateServiceModel;
import com.example.arsenalfinalproject.model.view.ProductsViewModel;
import com.example.arsenalfinalproject.service.ProductService;
import com.example.arsenalfinalproject.service.UserService;
import com.example.arsenalfinalproject.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public ProductController(ProductService productService, ModelMapper modelMapper, UserService userService) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @GetMapping("/all")
    public String allProduct(Model model , Principal principal) {

        model.addAttribute("allProducts", productService.getAllProducts());


        if (principal != null) {

            model.addAttribute("isAdmin", userService.isAdmin(principal.getName()));
        }

        return "product";
    }

    //UPDATE BY ADMIN
    @GetMapping("/{id}/edit")
    public String editProduct(@PathVariable Long id, Model model ) {

        checkIdExist(id,model);

        ProductsViewModel productsViewModel = productService.findById(id);

        ProductUpdateBindingModel productUpdateBindingModel = modelMapper.map(
                productsViewModel
                , ProductUpdateBindingModel.class);

        model.addAttribute("productModel", productUpdateBindingModel);


        return "editproduct";

    }

    @PatchMapping("/{id}/edit")
    public String editProduct(@PathVariable Long id,
                              @Valid ProductUpdateBindingModel productUpdateBindingModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("productUpdateBindingModel", productUpdateBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.productUpdateBindingModel",
                    bindingResult);
            return "redirect:"+ "/" + id + "/edit/errors";

        }

        ProductUpdateServiceModel productUpdateServiceModel =
                modelMapper.map(productUpdateBindingModel , ProductUpdateServiceModel.class);

        productUpdateServiceModel.setId(id);

        productService.updateOffer(productUpdateServiceModel);
        return "redirect:/product/all";
    }

    // Admin add new product

    @GetMapping("/add")
    public String getAddOfferPage(Model model){

        if(!model.containsAttribute("productAddBindingModel")) {
                model.addAttribute("productAddBindingModel" , new ProductAddBindingModel());
        }

        return "addproduct";
    }


    @PostMapping("/add")
    public String addProduct(@Valid ProductAddBindingModel productAddBindingModel ,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddBindingModel" , productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel"
                    , bindingResult);

            return "redirect:/product/add";
        }

         productService.addProduct(productAddBindingModel);

        return "redirect:/product/all";

    }
        //DELETE
    @Transactional
    @PreAuthorize("@productServiceImpl.isOwner(#principal.name , #id)")
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id , Principal principal ,
                                @RequestParam("public_id") String publicId ){

        productService.deleteProduct(id , publicId);

        return "redirect:/product/all";
    }



    private void checkIdExist(Long id , Model model) {
        boolean isExist = productService.isExistId(id);

        if (!isExist) {
            model.addAttribute("wrongId", id);
            throw new ObjectNotFoundException(id);
        }
    }


}
