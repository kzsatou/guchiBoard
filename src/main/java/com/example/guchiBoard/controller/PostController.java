package com.example.guchiBoard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.guchiBoard.dto.PostForm;
import com.example.guchiBoard.entity.Post;
import com.example.guchiBoard.service.PostService;

/**
 * 投稿 Controller
 */
@Controller
public class PostController {
	

    /**
     * 投稿 Service
     */
    @Autowired
    private PostService postService;
	
    /**
     * ユーザー情報一覧画面を表示
     * @param model Model
     * @return ユーザー情報一覧画面
     */
    @GetMapping(value = "/")
    public String displayList(Model model) {
        List<Post> postList = postService.findAll();
        model.addAttribute("postlist", postList);
        model.addAttribute("postForm", new PostForm());
        return "main/main";
    }
    
    /**
     * 投稿画面を表示
     * @param model Model
     * @return 投稿一覧画面
     */
    @GetMapping(value = "/user/add")
    public String displayAdd(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "user/add";
    }
    
    /**
     * 投稿新規登録
     * @param userRequest リクエストデータ
     * @param model Model
     * @return 投稿一覧画面
     */
	/* @RequestMapping(value = "/user/create", method = RequestMethod.POST) */
	@RequestMapping(value = "/post") /* , method = RequestMethod.POST */
    /*public String create(@Validated @ModelAttribute PostForm postForm, BindingResult result, Model model) {*/ //validationは入力のチェック
	public String create(@ModelAttribute PostForm postForm, BindingResult result, Model model) {
		/*
		 * if (result.hasErrors()) { // 入力チェックエラーの場合 List<String> errorList = new
		 * ArrayList<String>(); for (ObjectError error : result.getAllErrors()) {
		 * errorList.add(error.getDefaultMessage()); }
		 * model.addAttribute("validationError", errorList); return "user/add"; }
		 */
        // ユーザー情報の登録
        PostForm post = new PostForm();
        //1, "testを実施するためのタイトル", "投稿のテストをしています、適当な値を入力していますよ"
        long n = 1;
        post.setId(n);
        post.setTitle("testを実施するためのタイトル");
        post.setPost("投稿のテストをしています、適当な値を入力していますよ");
        postService.add(post);//postFormに直書きで値を入れてnewする,画面を介さずにDB周りが正しいか確認できる
        /*selectで表示する、ボタン押下で処理が通るかどうか*/
        return "post/new";
    }
}
