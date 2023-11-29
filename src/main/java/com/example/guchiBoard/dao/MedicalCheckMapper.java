package com.example.guchiBoard.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.guchiBoard.dto.MedicalCheckForm;
import com.example.guchiBoard.entity.MedicalCheck;

/**
 * 投稿 Mapper
 */
@Mapper
public interface MedicalCheckMapper {
	
	/**
	 * 投稿登録
	 * 
	 * @param postForm 登録用リクエストデータ
	 */
	void addMedical(MedicalCheckForm medicalForm);
	
	/**
	 * 表示有効/無効
	 * 
	 * @param postForm 登録用リクエストデータ
	 */
	void displayMedical(int userId, int checkYear);

	/**
	 * 検索登録
	 * 
	 * @param postForm 登録用リクエストデータ
	 */
	//String searchImage(int userId, int checkYear);
	String searchImage(int userId);
}
