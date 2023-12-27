package com.example.guchiBoard.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.guchiBoard.entity.User;

/**
 * ユーザー検索 Mapper
 */
@Mapper
public interface UserMapper {

	 Optional<User> findByEmail(String email);
}
