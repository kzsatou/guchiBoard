package com.example.guchiBoard.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.guchiBoard.dao.UserMapper;
import com.example.guchiBoard.dto.UserForm;
import com.example.guchiBoard.entity.User;

/**
 * ユーザー Service
 */
@Service
public class UserService {

	  /**
     * ユーザー情報 Mapper
     */
    @Autowired
    private UserMapper userMapper;
    
    /**
     * ユーザー情報検索
　　　* @param email メールアドレス
     * @return 正しいユーザーか
     */
    public Optional<User> findByEmail(String email) {
        return userMapper.findByEmail(email);
    }
    
    /**
     * ユーザー登録
　　　	 * @param 
     * @return 正しいユーザーか
     */
    public void addUser(UserForm userForm) {
        userMapper.addUser(userForm);
    }
    
}
