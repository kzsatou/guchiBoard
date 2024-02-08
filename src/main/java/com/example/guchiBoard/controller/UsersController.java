package com.example.guchiBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.guchiBoard.dto.UserForm;

@Controller
public class UsersController {

    @GetMapping(path = "/user/new")
    public String newUser(Model model) {
        //model.addAttribute("form", new UserForm());
        return "user/new";
    }
    
	@GetMapping(value = "/signup")
	public String signup(Model model) {
		// ユーザーフォーム
		model.addAttribute("UserForm", new UserForm());
		return "user/signup";
	}
}
