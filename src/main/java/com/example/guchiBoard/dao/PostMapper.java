package com.example.guchiBoard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

import com.example.guchiBoard.dto.PostForm;
import com.example.guchiBoard.entity.Post;
import com.example.guchiBoard.entity.Reply;
import com.example.guchiBoard.entity.Tags;

/**
 * 投稿 Mapper
 */
@Mapper
public interface PostMapper {

	/**
	 * idのシーケンスを挿入
	 */
	/*
	 * @Insert("INSERT INTO post_test (id) VALUES(LAST_INSERT_ID(1)) " +
	 * "ON DUPLICATE KEY UPDATE value = LAST_INSERT_ID(value + 1)")
	 * 
	 * @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before
	 * = false, resultType = long.class)
	 */
	/**
	 * 投稿全件検索
	 * 
	 * @param Post 検索用リクエストデータ
	 * @return 検索結果
	 */
	List<Post> findAll();
	//public Post findAll();
	
	/**
	 * 返信検索
	 * 
	 * @param Post 検索用リクエストデータ
	 * @return 検索結果
	 */
	//List<Reply> findReply();
	
	/**
	 * 投稿タグ全件検索
	 * 
	 * @param Tags 検索用リクエストデータ
	 * @return 検索結果
	 */
	List<Tags> findtagsAll();

	/**
	 * 投稿登録
	 * 
	 * @param postForm 登録用リクエストデータ
	 */
	void add(PostForm postForm);

	/*
	 * Post select(PostForm postForm); //戻り値がPost型
	 */}
