package com.example.guchiBoard.entity;

import java.util.Date;

import lombok.Data;

/**
 * 返信 Entity
 */
@Data
public class Reply {
	/**
     * ID
     */
    private Long id;
    
	/**
     * 返信対象の投稿コード
     */
    private Long postId;
    
    /**
     * 返信内容
     */
    private String replyComments;
    
    /**
     * 更新日時
     */
    private Date updateDate;
    /**
     * 登録日時
     */
    private Date createDate;
    
    private Post post;

}
