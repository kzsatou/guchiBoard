package com.example.guchiBord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.guchiBoard.dao.PostMapper;
import com.example.guchiBoard.entity.Post;
import com.example.guchiBoard.entity.Reply;

/*
 * @Component public class CommandlineappliApplication implements
 * CommandLineRunner{
 * 
 * @Autowired private PostMapper projectMapper;
 * 
 * @Override public void run(String... args) throws Exception { for (Post
 * project : projectMapper.findAll()) { System.out.printf("%s%n", project);
 * 
 * for (Reply ticket : project.getReplyList()) { System.out.printf("  └ %s%n",
 * ticket);
 * 
 * } } } }
 */
