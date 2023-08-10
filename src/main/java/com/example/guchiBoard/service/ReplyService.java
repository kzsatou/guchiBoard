package com.example.guchiBoard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.guchiBoard.dao.ReplyMapper;
import com.example.guchiBoard.dto.ReplyForm;
import com.example.guchiBoard.entity.Reply;

/**
 * 返信 Service
 */
@Service
public class ReplyService {
	
    /**
     * 返信 Mapper
     */
    @Autowired
    private ReplyMapper replyMapper;
    
    /**
     * 検索 Mapper
     */
    public Reply findReply(int postId) {
    	return replyMapper.findReply(postId);
    }
    
    /**
     * 返信登録
     * @param reply リクエストデータ
     */
    public void addReply(ReplyForm replyForm) {
    	//バリデーション（相関チェック）チェック処理を入れる、後々
    	replyMapper.addReply(replyForm);
    }

}
