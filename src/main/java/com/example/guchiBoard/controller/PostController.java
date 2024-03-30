package com.example.guchiBoard.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.guchiBoard.dto.MedicalCheckForm;
import com.example.guchiBoard.dto.PostForm;
import com.example.guchiBoard.dto.ReplyForm;
import com.example.guchiBoard.entity.MedicalCheck;
import com.example.guchiBoard.entity.Post;
import com.example.guchiBoard.entity.Reply;
import com.example.guchiBoard.entity.Tags;
import com.example.guchiBoard.service.DownloadService;
import com.example.guchiBoard.service.MedicalCheckService;
import com.example.guchiBoard.service.PollyService;
import com.example.guchiBoard.service.PostService;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import com.example.guchiBoard.config.AppConfig;
import com.example.guchiBoard.config.LoginUserDetails;
import com.example.guchiBoard.dao.PostMapper;

/**
 * 投稿 Controller
 */
@Controller
@RequiredArgsConstructor
public class PostController {

	/**
	 * 投稿 Service
	 */
	private final PostService postService;
	@Autowired
	private MedicalCheckService medicalcheckService;
	@Autowired
	private DownloadService downloadService;

	@Autowired
	private ModelMapper modelMapper;

	// ダウンロードボタンの表示が有効/無効
	boolean display = false;
	
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

	/**
	 * 新規作成画面の表示
	 * 
	 * @param model Model
	 * @return 新規投稿画面
	 */
	@GetMapping(value = "/post/new")
	public String newTopic(Model model) {
		// タグの表示
		List<Tags> tagsList = postService.findtagsAll();
		model.addAttribute("tagslist", tagsList);
		// 投稿フォーム
		model.addAttribute("postForm", new PostForm());
		return "post/new";
	}

	/**
	 * 投稿の詳細(参照)画面に遷移
	 * 
	 * @param long postId, model Model, Post form
	 * @return 投稿詳細画面
	 */
	@RequestMapping(value = "/post/{postId}/detail")
	public String newComment(@ModelAttribute MedicalCheckForm medicalForm, @PathVariable("postId") long postId,
			Model model, Post form) {

		medicalForm = new MedicalCheckForm();
		model.addAttribute("medicalForm", medicalForm);
		List<Post> postOne = postService.findOne(postId);
		/* ファイルを表示する処理(仮) */
		/*テスト処理 音声読み上げ*/
		//String text = "動作確認テストです。";
		//postService.readText(text);
		// ※userIDを取得する(未実装,postIDからユーザーIDを特定する?)
		int userId = 2;
		String base64Data = "";
		/*ディスプレイ*/
		//display = false;
		// 表示有効にしている健康診断結果のみダウンローダーを表示
		List<MedicalCheck> downloader =
		medicalcheckService.downloaderMedical(userId);
		model.addAttribute("downloader", downloader);
		 
		// 表示有効にしている健康診断結果のみ表示
		/*
		 * try { base64Data = medicalcheckService.outputImage(userId); } catch
		 * (IOException e) { // 例外処理 base64Data = ""; }
		 */

		model.addAttribute("base64Data", "data:application/pdf;base64," + base64Data);
		// model.addAttribute("pdf","data:application/pdf;base64,"+base64Data);

		model.addAttribute("postOne", postOne);

		return "post/detail";
	}

	/**
	 * 投稿新規登録
	 * 
	 * @param userRequest リクエストデータ
	 * @param model       Model
	 * @return 投稿新規登録画面
	 */
	@RequestMapping(value = "/post/add", method = RequestMethod.POST)
	public String create(@Validated @ModelAttribute PostForm postForm, BindingResult result, Model model) {

		if (result.hasErrors()) { // 入力チェックエラーの場合 List<String> errorList = new
			ArrayList<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "post/new";
		}

		postService.add(postForm);
		return "redirect:/main";
	}

	/**
	 * 公開設定
	 * 公開に設定した健康診断結果を表示
	 * @param userID,year
	 */
	@RequestMapping(value = "/post/display", method = RequestMethod.POST)
	public String displayCheck(@ModelAttribute MedicalCheckForm medicalForm, int userId, int checkYear, Model model)
			throws IOException {
		System.out.println("/post/displayの確認");
		medicalcheckService.displayMedical(userId, checkYear);
		return "/main";
	}
	
	/**
	 * 非公開設定
	 * 非公開に設定した健康診断結果を削除
	 * @param userID,year
	 */
	@RequestMapping(value = "/post/hide", method = RequestMethod.POST)
	public String hideCheck(@ModelAttribute MedicalCheck downloader, @AuthenticationPrincipal LoginUserDetails user,/* @RequestParam int year,*/ Model model)
			throws IOException {
		System.out.println("/post/hideの確認");
		System.out.println("--- downloader ---" + downloader);
		System.out.println("--- year --- " + downloader.getYear());
		int userId = 2; //テスト用
		medicalcheckService.hideMedical(userId, downloader.getYear());
		return "/main";
	}

	/**
	 * ファイルダウンロードAPI
	 * 
	 * @param file アップロードしたファイル
	 */
	@PostMapping(value = "/downloadFile")
	public void downloadFile(@AuthenticationPrincipal LoginUserDetails user, int year, HttpServletResponse response) {
		// ※userIDを取得する(認証機能実装後に正式な処理を実装,postIDからユーザーIDを特定する?)
		// 処理方法については考える
		System.out.println("downloadFile");
		System.out.println(user.getId());
		System.out.println(year);
		//System.out.println(year);
		int userId = 2;
		//int year = 2023;
		String imgFilePath;
		imgFilePath = medicalcheckService.searchImage(userId, year);
		//imgFilePath = "./src/main/webapp/uploads/sample_.pdf";
		downloadService.fileDownload(response, imgFilePath);
	}
	
	/**
	 * 音声読み上げAPI
	 * @param file アップロードしたファイル
	 */
	@PostMapping(value = "/polly")
	public void polly(String text) {
		System.out.println(text);
		/*テスト処理 音声読み上げ*/
		postService.readText(text);
	}
}
