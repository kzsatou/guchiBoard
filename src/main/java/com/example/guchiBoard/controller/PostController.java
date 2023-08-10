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
import com.example.guchiBoard.entity.Reply;
import com.example.guchiBoard.entity.Tags;
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
	 * メイン画面を表示
	 * 
	 * @param model Model
	 * @return メイン画面
	 */
	@GetMapping(value = "/main")
	public String displayList(Model model) {
		List<Post> postList = postService.findAll();
		//List<Post> replyList = Post.replyList;
		model.addAttribute("postlist", postList);
		model.addAttribute("postForm", new PostForm());
		return "main/main";
	}

	/**
	 * 投稿画面を表示
	 * 
	 * @param model Model
	 * @return 投稿一覧画面
	 */
	@GetMapping(value = "/post/new")
	public String newTopic(Model model) {
		//タグの表示
		List<Tags> tagsList = postService.findtagsAll();
		model.addAttribute("tagslist", tagsList);
		//投稿フォーム
		model.addAttribute("postForm", new PostForm());
		return "post/new";
	}

	/**
	 * 投稿新規登録
	 * 
	 * @param userRequest リクエストデータ
	 * @param model       Model
	 * @return 投稿一覧画面
	 */
	/* @RequestMapping(value = "/user/create", method = RequestMethod.POST) */
	@RequestMapping(value = "/post/add") /* , method = RequestMethod.POST */
	public String create(@ModelAttribute PostForm postForm, BindingResult result, Model model) { // validationは入力のチェック
		/*
		 * public String create(@Validated @ModelAttribute PostForm postForm, BindingResult result,
		 * Model model) {
		 */

		/*
		 * if (result.hasErrors()) { // 入力チェックエラーの場合 List<String> errorList = new
		 * ArrayList<String>(); for (ObjectError error : result.getAllErrors()) {
		 * errorList.add(error.getDefaultMessage()); }
		 * model.addAttribute("validationError", errorList); return "post/new"; }
		 */

		// ユーザー情報の登録
		/* test用
		 * PostForm post = new PostForm(); // 1, "testを実施するためのタイトル",
		 * "投稿のテストをしています、適当な値を入力していますよ" long n = 2; post.setId(n);
		 * post.setTitle("testを実施するためのタイトル2");
		 * post.setPost("投稿のテストをしています、適当な値を入力していますよ2");
		 */
		postService.add(postForm);// postFormに直書きで値を入れてnewする,画面を介さずにDB周りが正しいか確認できる
		/* selectで表示する、ボタン押下で処理が通るかどうか */
		return "redirect:/main";
	}
}
