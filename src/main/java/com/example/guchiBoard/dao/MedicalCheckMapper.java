package com.example.guchiBoard.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.guchiBoard.dto.MedicalCheckForm;

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

}
