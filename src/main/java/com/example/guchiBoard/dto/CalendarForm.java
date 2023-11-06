package com.example.guchiBoard.dto;

import java.io.Serializable;
import java.util.Date;

import com.example.guchiBoard.entity.Calendar;

import lombok.Data;

@Data
public class CalendarForm implements Serializable{
	
	/**
     * タイトル
     */
    private String title;
    
	/**
     * 開始日
     */
    private String start;
    
	/**
     * 終了日
     */
    private String end;
        
	/**
     * コメント
     */
    private String description;
}
