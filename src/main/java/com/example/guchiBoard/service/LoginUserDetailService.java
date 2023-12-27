package com.example.guchiBoard.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.guchiBoard.config.LoginUserDetails;
import com.example.guchiBoard.dao.UserMapper;
import com.example.guchiBoard.entity.User;

@Service
public class LoginUserDetailService implements UserDetailsService {
	
	private final UserMapper userMapper;

	  public LoginUserDetailService(UserMapper userMapper) {
	    this.userMapper = userMapper;
	  }

	  @Override
	  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    Optional<User> _user = userMapper.findByEmail(email);
	    return _user.map(user -> new LoginUserDetails(user))
	        .orElseThrow(() -> new UsernameNotFoundException("not found email=" + email));
	  }

}
