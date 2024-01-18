package com.example.guchiBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.guchiBoard.dto.PostForm;
import com.example.guchiBoard.entity.Post;
import com.example.guchiBoard.entity.Tags;
import com.example.guchiBoard.service.PostService;

/**
 * ホーム画面に遷移 Controller
 */
public class MainController {

	/**
	 * 投稿 Service
	 */
	@Autowired
	private PostService postService;

	/**
	 * メイン画面を表示
	 * 
	 * @param Model model, Post form
	 * @return メイン画面
	 */
	@GetMapping(value = "/main")
	public String displayList(Model model, Post form) {
		List<Post> postList = postService.findAll();
		model.addAttribute("postlist", postList);

		/*デバッグ用処理*/
		System.out.println("postlistのサイズ" + postList.size());
		for (Post post : postList) {
			System.out.println(post.getReplyList().size());
		}

		model.addAttribute("postForm", new PostForm());

		// タグの表示
		List<Tags> tagsList = postService.findtagsAll();
		model.addAttribute("tagslist", tagsList);

		return "main/main";
	}
}
