package com.example.guchiBoard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.guchiBoard.dto.PostForm;
import com.example.guchiBoard.entity.Post;
import com.example.guchiBoard.service.PostService;

/**
 * 検索したタグが付いたコメント一覧画面 Controller
 */
@Controller
public class PostSearchController {

	/**
	 * 投稿 Service
	 */
	@Autowired
	private PostService postService;

	/**
	 * 選択したタグのあるコメント一覧画面を表示
	 * 
	 * @param PostForm postForm, long[] tagCodes, model Model
	 * @return コメント検索画面
	 */
	@RequestMapping(value = "/post/tagsearch", method = RequestMethod.POST)
	public String tagSearch(@ModelAttribute PostForm postForm, long[] tagCodes, Model model) throws IOException {

		System.out.println("tagSearchの確認");
		List<Post> postTagList = new ArrayList<Post>();

		// 選択されたタグ分だけ、タグ付された投稿を全検索する
		for (int i = 0; i < tagCodes.length; i++) {
			postTagList.addAll(postService.findtagComment(tagCodes[i]));
		}

		model.addAttribute("posttaglist", postTagList);
		return "/post/tagComment";
	}
}
