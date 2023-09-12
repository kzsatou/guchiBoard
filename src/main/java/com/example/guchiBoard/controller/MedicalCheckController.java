package com.example.guchiBoard.controller;

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
	public String create(@ModelAttribute MedicalCheckForm medicalForm, BindingResult result, Model modele, @RequestParam("mFile") MultipartFile mFile) {
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
}
