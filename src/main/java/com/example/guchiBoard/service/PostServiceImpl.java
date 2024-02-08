package com.example.guchiBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.guchiBoard.dao.PostMapper;
import com.example.guchiBoard.dto.PostForm;
import com.example.guchiBoard.entity.Post;
import com.example.guchiBoard.entity.Tags;

/**
 * 投稿 Service
 */
@Service
public class PostServiceImpl implements PostService {
    /**
     * 投稿 Mapper
     */
    @Autowired
    private PostMapper postMapper;
    
    @Override
    /**
     * 投稿全件検索
     * @return 検索結果
     */
    public List<Post> findAll() {
    //public Post findAll() {
        return postMapper.findAll();
    }
    
    /**
     * 投稿1件検索
     * @return 検索結果
     */
    public List<Post> findOne(long id) {
        return postMapper.findOne(id);
    }
    
    /**
     * 返信検索
     * @return 検索結果
     */
	/*
	 * public List<Reply> findReply() { return postMapper.findReply(); }
	 */
    
    /**
     * 投稿タグ全件検索
     * @return 検索結果
     */
    public List<Tags> findtagsAll() {
        return postMapper.findtagsAll();
    }
    
    /**
     * 投稿タグからコメントを検索して表示
     * @return 検索結果
     */
    public List<Post> findtagComment(long tagCode) {
        return postMapper.findtagComment(tagCode);
    }
    
    /**
     * 投稿登録
     * @param post リクエストデータ
     */
    public void add(PostForm postForm) { //PostAddRequest⇒Post
        //バリデーション（相関チェック）チェック処理を入れる、後々
    	postMapper.add(postForm);
    }
}

