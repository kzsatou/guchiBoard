package com.example.guchiBoard.entity;

import java.util.Date;

import lombok.Data;

/**
 * 投稿 Entity
 */
@Data
public class Calendar {
	
	/**
     * ID
     */
	private Long Id;

	/**
     * 開始日
     */
    private Date startDate;
    
	/**
     * 終了日
     */
    private Date endDate;
    
	/**
     * タイトル
     */
    private String title;
    
	/**
     * コメント
     */
    private String comment;
    
    /**
     * 更新日時
     */
    private Date updateDate;
    
    /**
     * 登録日時
     */
    private Date createDate;
}
