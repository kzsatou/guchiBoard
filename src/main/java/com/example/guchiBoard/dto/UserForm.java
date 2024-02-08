package com.example.guchiBoard.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserForm implements Serializable {

	/**
	 * ユーザー名
	 */
	// @NotEmpty(message = "id未登録")
	private String userName;
	
	/**
	 * メールアドレス
	 */
	private String eMail;

	/**
	 * パスワード
	 */
	private String passWord;
	
	/**
	 * パスワード確認
	 */
	private String passwordConfirm;
	
	/**
	 * ロール
	 */
	private String roles;
}
