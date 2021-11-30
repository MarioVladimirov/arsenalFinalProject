package com.example.arsenalfinalproject.web;

import com.example.arsenalfinalproject.model.binding.TopicHistoryBindingModel;
import com.example.arsenalfinalproject.model.service.TopicHistoryServiceModel;
import com.example.arsenalfinalproject.service.MemberTopicService;
import com.example.arsenalfinalproject.service.impl.ArsenalUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/api")
public class HistoryUserController {


    public final MemberTopicService memberTopicService;
    public final ModelMapper modelMapper;

    public HistoryUserController(MemberTopicService memberTopicService, ModelMapper modelMapper) {
        this.memberTopicService = memberTopicService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/historyuser/add")
    public String viewHistory(Model model){

        if(!model.containsAttribute("topicHistoryBindingModel")) {
            model.addAttribute("topicHistoryBindingModel" , new TopicHistoryBindingModel());
        }

        return "add-historyfans";
    }

    @PostMapping("/historyuser/add")
    public String addHistory(@Valid TopicHistoryBindingModel topicHistoryBindingModel ,
                             BindingResult bindingResult ,
                             RedirectAttributes redirectAttributes ,
                             @AuthenticationPrincipal ArsenalUser currentUser) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("topicHistoryBindingModel", topicHistoryBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.topicHistoryBindingModel", bindingResult);

            return "redirect:/api/historyuser/add";

        }

        TopicHistoryServiceModel topicHistoryServiceModel = modelMapper.map(topicHistoryBindingModel , TopicHistoryServiceModel.class);

        memberTopicService.addTopicHistoryUser(topicHistoryServiceModel , currentUser.getUserIdentifier());

        redirectAttributes.addFlashAttribute("addNewHistoryByUser" , "Your story is being processed");

        return "redirect:/";
    }

}
