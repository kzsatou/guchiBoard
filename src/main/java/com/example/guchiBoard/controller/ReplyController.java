package com.example.guchiBoard.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.guchiBoard.dto.PostForm;
import com.example.guchiBoard.dto.ReplyForm;
import com.example.guchiBoard.service.ReplyService;

/**
 * 返信 Controller
 */
@Controller
public class ReplyController {

	/**
	 * 返信 Service
	 */
	@Autowired
	private ReplyService replyService;

	@RequestMapping(value = "/post/{postId}/reply/new")
	// @GetMapping(value = "/post/{id}/reply/new")
	public String newComment(@PathVariable("postId") long postId, Model model) {

		ReplyForm replyForm = new ReplyForm();
		replyForm.setPostId(postId);
		model.addAttribute("replyForm", replyForm);

		return "reply/new";
	}

	/**
	 * 返信新規登録
	 * 
	 * @param userRequest リクエストデータ
	 * @param model       Model
	 * @return 投稿一覧画面
	 */
	@RequestMapping(value = "/reply/{postId}/add", method = RequestMethod.POST) /* , method = RequestMethod.POST */
	public String create(@PathVariable("postId") long postId, @Validated @ModelAttribute ReplyForm replyForm,
			BindingResult result, Model model) {

		if (result.hasErrors()) { // 入力チェックエラーの場合 List<String> errorList = new
			ArrayList<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);

			return "reply/new";
			// return "/post/{id}/reply/new";
		}

		replyForm.setPostId(postId);
		replyService.addReply(replyForm);

		return "redirect:/main";
	}

}