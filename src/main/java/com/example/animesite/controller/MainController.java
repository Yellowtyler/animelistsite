package com.example.animesite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(Model model)
    {

        return "main";
    }


    @GetMapping("list")
    public String getList()
    {
        return "list";
    }
}
