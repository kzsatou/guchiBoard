package com.example.guchiBoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	 @RequestMapping(value = "/post/{id}/reply/new")
	    public String newComment(@PathVariable("id") long id, Model model) {
			
			 ReplyForm replyForm = new ReplyForm(); 
			 replyForm.setPostId(id);
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
	 @RequestMapping(value = "/reply/{id}/add") /* , method = RequestMethod.POST */
		public String create(@PathVariable("id") long id, @ModelAttribute("replyForm") ReplyForm replyForm, BindingResult result, Model model) { 
		 replyForm.setPostId(id);	  
		 replyService.addReply(replyForm);
		 	  
			return "redirect:/main";
	 }

}