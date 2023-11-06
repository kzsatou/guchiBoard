package com.example.guchiBoard.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.guchiBoard.dto.MedicalCheckForm;
import com.example.guchiBoard.service.MedicalCheckService;

/**
 * 投稿 Controller
 */
@Controller
public class MedicalCheckController {

	/**
	 * 投稿 Service
	 */
	@Autowired
	private MedicalCheckService medicalService;

	@GetMapping(value = "/medicalcheck/new")
	public String newMedicalCheck(Model model) {
		model.addAttribute("medicalForm", new MedicalCheckForm());
		return "medicalcheck/new";
	}

	/**
	 * 健康新規登録
	 * 
	 * @param userRequest リクエストデータ
	 * @param model       Model
	 * @return 投稿一覧画面
	 */
	@RequestMapping(value = "/medicalcheck/add", method = RequestMethod.POST)
	public String create(@ModelAttribute MedicalCheckForm medicalForm, BindingResult result, Model model, @RequestParam("mFile") MultipartFile mFile) {
		/* , @RequestParam("mFile") MultipartFile mFil */

		//フォームで渡されてきたアップロードファイルを取得
        MultipartFile multipartFile = medicalForm.getMultipartFile();
		//MultipartFile multipartFile = medicalForm.mFile;
		
		String filePath = medicalService.saveImageLocal(multipartFile);
		//String filePath = medicalService.saveImageLocal(mFile);
		
		//ユーザーIDをテスト用に生成
		long n = 1;
		medicalForm.setUserId(n);
		//medicalForm.setPdfPath(filePath);
		medicalForm.setPicturePath(filePath);
		medicalService.addMedical(medicalForm);

		return "redirect:/main";
	}
	
	/**
	 * 健康診断表示
	 * 
	 * @param userRequest リクエストデータ
	 * @param model       Model
	 * @return 投稿一覧画面
	 */
	@RequestMapping(value = "/medicalcheck/upload", method = RequestMethod.POST)
    /**
     * 画像表示処理
     * @param multipartFile
     */
	public String outputImage(@ModelAttribute MedicalCheckForm medicalForm, int userId, int checkYear, Model model) throws IOException {
		System.out.println("methodの確認"); 
		System.out.println(userId); 
		System.out.println(checkYear); 
		String imgFilePath = medicalService.searchImage(userId, checkYear);
		
		File fileImg = new File(imgFilePath);
		try {
			byte[] byteImg = Files.readAllBytes(fileImg.toPath());
			String base64Data = Base64.getEncoder().encodeToString(byteImg);
			model.addAttribute("base64Data","data:image/png;base64,"+base64Data);
		}catch(IOException e) {
			return null;
		}
		return "image";
	}
	
}
