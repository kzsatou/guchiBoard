package com.example.guchiBoard.controller;

import java.net.URI;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.guchiBoard.dto.UserForm;
import com.example.guchiBoard.service.UserRegistrationService;

@Controller
public class UsersController {
	
	/**
	 * 投稿 Service
	 */
	@Autowired
	private UserRegistrationService userRegistrationService;

    @GetMapping(path = "/user/new")
    public String newUser(Model model) {
        //model.addAttribute("form", new UserForm());
        return "user/new";
    }
    
	/* @GetMapping(value = "/signup") */
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
    	System.out.println("サインアップ");
		// ユーザーフォーム
		model.addAttribute("UserForm", new UserForm());
		return "user/signup";
	}
    
    @RequestMapping(value = "/user/signup", method = RequestMethod.GET)
    public String displayAdd(Model model) {
    	System.out.println("サインアップリダイレクト");
    	model.addAttribute("UserForm", new UserForm());
        return "user/signup";
    }
	
    // ユーザー登録のエンドポイント ResponseEntity<String>
	@RequestMapping(value = "/user", method = RequestMethod.POST)
    public String signup(@Validated @ModelAttribute UserForm userForm,
            BindingResult bindingResult, Model model) {

    	System.out.println("userメソッド");
        // バリデーションエラーのチェック
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(err -> err.getDefaultMessage())
                    // エラーメッセージをカンマで連結
                    .collect(Collectors.joining(", "));
			/* return redirectToErrorPage(errorMessage); */
			return "user/signup";
        }

        try {
            // ユーザー登録処理
        	System.out.println("ユーザー登録処理");
            userRegistrationService.registerUser(userForm);
			
			/*
			 * return
			 * ResponseEntity.status(HttpStatus.FOUND).location(URI.create("main/main.html")
			 * ) .body("User registered successfully.");
			 */
			 
			 return "/main" ; 
        } catch (Exception ex) {
            // 登録エラーのハンドリング
			/* return redirectToErrorPage(ex.getMessage()); */
        	  model.addAttribute("UserForm", new UserForm());
        	  model.addAttribute("validationError", ex.getMessage());
			return "user/signup"; 
        }
    }

    // エラーメッセージを持ったエラーページへのリダイレクト
    private ResponseEntity<String> redirectToErrorPage(String errorMessage) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/signup")
                .queryParam("error", errorMessage);

        URI errorPageUri = builder.build().encode().toUri();
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(errorPageUri)
                .body(errorMessage);
    }
}
