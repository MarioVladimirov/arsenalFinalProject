package com.example.arsenalfinalproject.web;

import com.example.arsenalfinalproject.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/product/all")
    public String allProduct(Model model) {

        model.addAttribute("allProducts" , productService.getAllProducts() );

        return "product";
    }

}
