package com.giosinosini.springboot3.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.giosinosini.springboot3.security.UserSpringSecurity;

public class UserService {
	
	public static UserSpringSecurity authenticated() {  // return logged user
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
		}
		catch (Exception e) {
			return null;
		}
	}
}
