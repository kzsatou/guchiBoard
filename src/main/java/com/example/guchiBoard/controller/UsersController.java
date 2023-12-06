package com.example.guchiBoard.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class UsersController {

    @GetMapping(path = "/user/new")
    public String newUser(Model model) {
        //model.addAttribute("form", new UserForm());
        return "user/new";
    }
}
