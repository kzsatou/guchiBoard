package com.example.guchiBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.guchiBoard.dto.MedicalCheckForm;
import com.example.guchiBoard.entity.Calendar;
import com.example.guchiBoard.service.CalendarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 投稿 Controller
 */
@Controller
public class CalendarController {
	
	/**
	 * 投稿 Service
	 */
	@Autowired
	private CalendarService calendarService;
	
	/**
	 * カレンダー Controller
	 * 画面遷移
	 */
	@GetMapping(value = "/calendar/calendar")
	public String newMedicalCheck(Model model) {
		//model.addAttribute("medicalForm", new MedicalCheckForm());
		System.out.println("test");
		return "calendar/calendar";
	}
	
	/**
	 * カレンダー
	 * データ取得
	 */
	@GetMapping("/search")
	@ResponseBody
	public String search() {
		System.out.println("test");
		//変数宣言
		String jsonData = null;
		
		List<Calendar> calendar = calendarService.findCalendar();
		/*変換を簡単にする*/
		ObjectMapper mapper = new ObjectMapper();
		
		/*json型に変換*/
		try {
			jsonData = mapper.writeValueAsString(calendar);
			System.out.println(jsonData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jsonData;
	}
}
