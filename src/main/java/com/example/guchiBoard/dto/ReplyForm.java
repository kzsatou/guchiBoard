package com.example.guchiBoard.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 返信登録 リクエストデータ 画面側で扱うデータの処理
 */
@Data
public class ReplyForm implements Serializable {
	/**
	 * 名前
	 */
	//@NotEmpty(message = "id未登録")
	private Long id;

	/**
	 * 返信の対象投稿コード
	 */
	private Long postId;
	
    /**
     * 返信内容
     */
    @NotEmpty(message = "返信本文を入力してください")
    @Size(min=20, max=1000)
    private String replyComments;

}
