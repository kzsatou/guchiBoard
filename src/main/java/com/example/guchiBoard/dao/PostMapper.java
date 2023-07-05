package com.example.guchiBoard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.guchiBoard.dto.PostForm;
import com.example.guchiBoard.entity.Post;

/**
 * 投稿 Mapper
 */
@Mapper
public interface PostMapper {

    /**
     * 投稿全件検索
     * @param Post 検索用リクエストデータ
     * @return 検索結果
     */
    List<Post> findAll();
    
    /**
     * 投稿登録
     * 
     * @param postForm 登録用リクエストデータ
     */
    void add(PostForm postForm);
    
	/*
	 * Post select(PostForm postForm); //戻り値がPost型
	 */}
