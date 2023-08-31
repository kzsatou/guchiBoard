package com.example.guchiBoard.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 投稿登録 リクエストデータ
 * 画面側で扱うデータの処理
 */
@Data
public class PostForm implements Serializable{ //PostForm
	   /**
     * 名前
     */
    //@NotEmpty(message = "id未登録")
    private Long id;
    
	   /**
  * 投稿タグコード
  */
    private Long tagCode;
    
	   /**
  * 投稿タグ
  */
    @NotEmpty(message = "タグを選択してください")
    private String tag;
    
    /**
     * 投稿タイトル
     */
    @NotEmpty(message = "タイトルを入力してください")
    @Size(min=10, max=50)
    private String title;
    
    /**
     * 投稿本文
     */
    @NotEmpty(message = "投稿本文を入力してください")
    @Size(min=20, max=1000)
    private String post;
}	
