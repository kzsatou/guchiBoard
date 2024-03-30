package com.example.guchiBoard.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 投稿 Entity
 */
@Data
public class User {

	/**
     * ID
     */
	 private Long id;

	/**
	 * email
	 */
	 private String email;
	 /**
	 * password
	 */
	 private String password;
	 /**
	 * name
	 */
	 private String name;
	 /**
	 * role
	 */
	 private String roles = "ROLE_USER";
	 
}
