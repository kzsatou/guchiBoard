package com.example.guchiBoard.service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.guchiBoard.dao.PostMapper;
import com.example.guchiBoard.dto.MedicalCheckForm;
import com.example.guchiBoard.dto.PostForm;
import com.example.guchiBoard.entity.MedicalCheck;
import com.example.guchiBoard.entity.Post;
import com.example.guchiBoard.entity.Reply;
import com.example.guchiBoard.entity.Tags;

/**
 * 投稿 Service
 */
@Service
public class PostService {
    /**
     * 投稿 Mapper
     */
    @Autowired
    private PostMapper postMapper;
    
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
     * 投稿登録
     * @param post リクエストデータ
     */
    public void add(PostForm postForm) { //PostAddRequest⇒Post
        //バリデーション（相関チェック）チェック処理を入れる、後々
    	postMapper.add(postForm);
    }
    
    /**
     * 投稿登録
     * @param post リクエストデータ
     */
    public MedicalCheckForm getMedicalCheck(MedicalCheck entity) {
    	MedicalCheckForm form = modelMapper.map(entity, MedicalCheckForm.class);
    	
    	 try (InputStream is = new FileInputStream(new File(entity.getPath()));
                 ByteArrayOutputStream os = new ByteArrayOutputStream()) {
             byte[] indata = new byte[10240 * 16];
             int size;
             while ((size = is.read(indata, 0, indata.length)) > 0) {
                 os.write(indata, 0, size);
             }
    }
}
}
