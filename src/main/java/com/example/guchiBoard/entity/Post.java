package com.example.guchiBoard.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 投稿 Entity
 */
@Data
public class Post {

	/**
     * ID
     */
    private Long id;
    
	/**
     * 投稿タグコード
     */
    private Long tagCode;
    
	/**
     * 投稿タグ名
     */
    private String tag;
    
    /**
     * タイトル
     */
    private String title;
    
    /**
     * 投稿内容
     */
    private String post;
    
    /**
     * 更新日時
     */
    private Date updateDate;
    
    /**
     * 登録日時
     */
    private Date createDate;
    
    /**
     * 返信リスト
     */
    List<Reply> replyList;
    
}
