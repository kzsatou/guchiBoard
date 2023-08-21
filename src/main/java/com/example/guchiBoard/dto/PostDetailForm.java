package com.example.guchiBoard.dto;

import java.util.Date;
import java.util.List;

import com.example.guchiBoard.entity.Reply;

import lombok.Data;

@Data
public class PostDetailForm {

	/*	*//**
			 * ID
			 */
	/*
	 * private Long id;
	 * 
	 *//**
		 * 投稿タグコード
		 */
	/*
	 * private Long tagCode;
	 * 
	 *//**
		 * 投稿タグ名
		 */
	/*
	 * private String tag;
	 * 
	 *//**
		 * タイトル
		 */
	/*
	 * private String title;
	 * 
	 *//**
		 * 投稿内容
		 */
	/*
	 * private String post;
	 * 
	 *//**
		 * 更新日時
		 */
	/*
	 * private Date updateDate;
	 * 
	 *//**
		 * 登録日時
		 *//*
			 * private Date createDate;
			 */

	/**
	 * 返信リスト
	 */
	private List<Reply> replyList;
}
