package com.example.guchiBoard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.guchiBoard.dto.ReplyForm;
import com.example.guchiBoard.entity.Reply;

@Mapper
public interface ReplyMapper {
	/**
	 * 投稿に対応する返信検索
	 * 
	 * @param Reply 検索用リクエストデータ
	 * @return 検索結果
	 */
	//List<Reply> findAll();
	Reply findReply(int postId);
	
	/**
	 * 投稿登録
	 * 
	 * @param replyForm 登録用リクエストデータ
	 */
	void addReply(ReplyForm replyForm);

}
