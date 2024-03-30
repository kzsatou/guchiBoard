package com.example.guchiBoard.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.guchiBoard.dto.UserForm;
import com.example.guchiBoard.entity.User;

@Service
public class UserRegistrationService {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	public UserRegistrationService(UserService userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	// 新しいユーザーを登録
	@Transactional
	public void registerUser(UserForm userForm) throws Exception {
		//User user = userService.getUserByUsername(userForm.getUserName());
		//バリデーションチェック用のメソッド、メインの登録処理以外はメソッドに出す
		//List<String> validation = validation(userForm);
		
		
		// 二重登録のチェック
		if (userService.getUserByUsername(userForm.getUserName()) != null) {
			throw new Exception("ユーザ名が既に存在します。");
		}

		// パスワード一致のチェック
		if (!userForm.getPassWord().equals(userForm.getPasswordConfirm())) {
			throw new Exception("パスワードと確認用パスワードが一致しません。");
		}

		// 新しいユーザーエンティティの作成と保存
		User user = new User();
		user.setName(userForm.getUserName());
		user.setEmail(userForm.getEMail());
		// パスワードをハッシュ化してセットする
		user.setPassword(passwordEncoder.encode(userForm.getPassWord()));
		userService.addUser(user);
	}
	
	//相関チェック
	/*
	 * private List<String> validation(userForm){ return; }
	 */
}
