package com.example.guchiBoard.entity;

import lombok.Data;

/**
 * 投稿タグマスタ Entity
 */
@Data
public class Tags {

	/**
     * ID
     */
    private Long id;
    
    /**
     * 投稿タグ内容
     */
    private String tag;
}
