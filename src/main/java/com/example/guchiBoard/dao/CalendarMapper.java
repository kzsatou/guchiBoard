package com.example.guchiBoard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.guchiBoard.entity.Calendar;

/**
 * 投稿 Mapper
 */
@Mapper
public interface CalendarMapper {

	/**
	 * カレンダー検索
	 * @param Calender 検索用リクエストデータ
	 * @return 検索結果
	 */
	List<Calendar> findCalendar();
	
	/**
	 * カレンダー登録
	 * @param Calender 登録用リクエストデータ
	 * @return 登録データ
	 */
	void addCalendar(Calendar calendar);
}
