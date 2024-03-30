package com.example.guchiBoard.service;

import java.util.List;
import com.example.guchiBoard.dto.PostForm;
import com.example.guchiBoard.entity.Post;
import com.example.guchiBoard.entity.Tags;

/**
 * Service Interface
 */

public interface PostService{
	public List<Post> findAll();
	
	public List<Post> findOne(long id);
	
	public List<Tags> findtagsAll();
	
	public List<Post> findtagComment(long tagCode);
	 
	public void add(PostForm postForm);
	
	public void readText(String text);
}


