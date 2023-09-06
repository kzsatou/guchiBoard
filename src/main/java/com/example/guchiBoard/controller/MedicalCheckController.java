package com.example.guchiBoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
