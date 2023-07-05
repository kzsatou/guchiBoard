package com.example.guchiBoard.entity;

import java.util.Date;

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
}
