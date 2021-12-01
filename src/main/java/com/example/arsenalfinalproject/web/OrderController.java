package com.example.arsenalfinalproject.web;

import com.example.arsenalfinalproject.model.binding.OrderBindingModel;
import com.example.arsenalfinalproject.model.view.MemberTopicView;
import com.example.arsenalfinalproject.model.view.OrderViewModel;
import com.example.arsenalfinalproject.service.MemberTopicService;
import com.example.arsenalfinalproject.service.OrderService;
import com.example.arsenalfinalproject.service.ProductService;
import com.example.arsenalfinalproject.service.UserService;
import com.example.arsenalfinalproject.service.impl.ArsenalUser;
import com.example.arsenalfinalproject.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

        private final OrderService orderService;
        private final ProductService productService;
        private final UserService userService;
        private final MemberTopicService memberTopicService;
        private final ModelMapper modelMapper;


    public OrderController(OrderService orderService, ProductService productService, UserService userService, MemberTopicService memberTopicService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
        this.memberTopicService = memberTopicService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/{id}")
    public String viewOrder(@PathVariable Long id,
                            Model model ,
                            @AuthenticationPrincipal ArsenalUser currentUser) {

        checkIdExist(id , model);


        OrderViewModel orderViewModel =
                orderService.findAllDataByIdAndCurrentUser(id,currentUser.getUserIdentifier());

        OrderBindingModel orderBindingModel = modelMapper.map(orderViewModel,OrderBindingModel.class);


        model.addAttribute("orderModel" , orderBindingModel);
        model.addAttribute("idProduct" , id);
        model.addAttribute("maxCount" , orderViewModel.getMaxCount());


        return "order";
    }

    @GetMapping("/allorderbyuser")
    public String allOrderByUser(Model model ,
                                 @AuthenticationPrincipal ArsenalUser currentUser) {


        model.addAttribute("allOrderByOneUser" ,
                orderService.findAllOrderForOneUserByCurrentUser(currentUser.getUserIdentifier()));
        model.addAttribute("usernameCurrent" , currentUser.getUserIdentifier());

            return "purchase";
    }



    @PostMapping("/{id}")
    public String newOrder(@PathVariable Long id ,
                           @Valid OrderBindingModel orderBindingModel ,
                           BindingResult bindingResult ,
                           RedirectAttributes redirectAttributes ,
                           @AuthenticationPrincipal ArsenalUser userName ,
                             Model  model) {

         checkIdExist(id , model);

        Integer countProduct = productService.findById(id).getCountProduct();

        if (bindingResult.hasErrors() || countProduct < orderBindingModel.getCount()) {
            redirectAttributes.addFlashAttribute("orderBindingModel", orderBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderBindingModel" , bindingResult);


           return  "redirect:/order/" + id;
        }

        orderService.saveNewOrder(id , orderBindingModel , userName.getUserIdentifier());

        //View front end message that you bought product
        Integer count = orderBindingModel.getCount();
        BigDecimal multiply = orderBindingModel.getPrice().multiply(BigDecimal.valueOf(count));


        redirectAttributes.addFlashAttribute("addOrder" ,
                "Your order is being prepared! Total:" + multiply + " lv.");

        return "redirect:/";
    }


    @GetMapping("/membership")
    public String buyMembership(Model model) {

        List<MemberTopicView> memberTopicAllLimit3 = memberTopicService.getMemberTopicAllLimit3();

        model.addAttribute("allTopicMember" , memberTopicAllLimit3);

        return "membership";
    }

    private void checkIdExist(Long id , Model model) {
        boolean isExist = productService.isExistId(id);

        if (!isExist) {
            model.addAttribute("wrongId", id);
            throw new ObjectNotFoundException(id);
        }
    }
}
