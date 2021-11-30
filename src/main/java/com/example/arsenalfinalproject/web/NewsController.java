package com.example.arsenalfinalproject.web;

import com.example.arsenalfinalproject.model.binding.NewsAddBindingModel;
import com.example.arsenalfinalproject.model.binding.NewsUpdateBindingModel;
import com.example.arsenalfinalproject.model.service.NewsAddServiceModel;
import com.example.arsenalfinalproject.model.service.NewsUpdateServiceModel;
import com.example.arsenalfinalproject.model.view.NewsDetailsView;
import com.example.arsenalfinalproject.service.NewsService;
import com.example.arsenalfinalproject.service.impl.ArsenalUser;
import com.example.arsenalfinalproject.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;
    private final ModelMapper modelMapper;

    public NewsController(NewsService newsService, ModelMapper modelMapper) {
        this.newsService = newsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/details/{id}")
    public String showNews(
            @PathVariable Long id, Model model, Principal principal) {

        //Check Id exist and throw exception 404
        checkIdExist(id,model) ;


        String userCurrent = "";

        if (principal != null) {
            userCurrent = principal.getName();
        }


        model.addAttribute("newsDetails", this.newsService
                .findById(id, userCurrent));

        return "details-news";
    }


    @PreAuthorize("@newsServiceImpl.isOwner(#principal.name , #id)")
    @GetMapping("/edit/{id}")
    public String updateNews(@PathVariable Long id, Model model,
                             @AuthenticationPrincipal ArsenalUser currentUser , Principal principal) {

        //Check Id exist and throw exception 404
        checkIdExist(id,model) ;

        NewsDetailsView newsDetailsView = newsService.findById(id,currentUser.getUserIdentifier());

        NewsUpdateBindingModel newsModel = modelMapper.map(newsDetailsView,NewsUpdateBindingModel.class);

        model.addAttribute("newsModel",newsModel);

        return "editnews";
    }

    @PatchMapping("/edit/{id}")
    public String editNews(@PathVariable Long id ,
                           @Valid NewsUpdateBindingModel newsUpdateBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes ,
                           @AuthenticationPrincipal ArsenalUser currentUser) {



        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("newsUpdateBindingModel", newsUpdateBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.newsUpdateBindingModel",
                    bindingResult);
            return "redirect:" + "/" + id + "/edit/errors";
        }


        NewsUpdateServiceModel newsUpdateServiceModel =
                modelMapper.map(newsUpdateBindingModel ,NewsUpdateServiceModel.class);

        newsUpdateServiceModel.setId(id);

        newsService.updateOffer(newsUpdateServiceModel);

        return "redirect:/news/details/" + id;
    }



    @GetMapping("/add")
    public String getAddNewsPage(Model model) {

        if (!model.containsAttribute("newsAddBindingMode")) {
            model.addAttribute("newsAddBindingMode", new NewsAddBindingModel());
        }


        return "addnews";
    }

    @PostMapping("/add")
    public String addNews(@Valid NewsAddBindingModel newsAddBindingModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          @AuthenticationPrincipal ArsenalUser user) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("newsAddBindingModel", newsAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newsAddBindingModel"
                    , bindingResult);

            return "redirect:/news/add";
        }

        NewsAddServiceModel newsAddServiceModel = newsService.addNews(newsAddBindingModel, user.getUserIdentifier());


        return "redirect:/news/details/" + newsAddServiceModel.getId();
    }


    //        DELETE
    @Transactional
    @PreAuthorize("@newsServiceImpl.isOwner(#principal.name , #id)")
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id, Principal principal, @RequestParam("public_id") String publicId) {

        newsService.deleteProduct(id, publicId);

        return "redirect:/";
    }


    private void checkIdExist(Long id , Model model) {
        boolean isExist = newsService.isExistId(id);

        if (!isExist) {
            model.addAttribute("wrongId", id);
            throw new ObjectNotFoundException(id);
        }
    }



}
