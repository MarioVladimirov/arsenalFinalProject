package com.example.arsenalfinalproject.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game/")
public class QuestController {


    @GetMapping("/quest")
    public String viewQuest() {


            return "quest";
    }


}
