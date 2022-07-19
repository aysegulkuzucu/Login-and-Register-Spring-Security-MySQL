package com.project.login.response;

import com.project.login.entity.User;

import lombok.Data;

@Data
public class UserResponse {
	
	Long id;
	String username;

	public UserResponse(User entity) {
		this.id = entity.getId();
		this.username = entity.getUsername();
	} 
}