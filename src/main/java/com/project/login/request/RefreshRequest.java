package com.project.login.request;

import lombok.Data;

@Data
public class RefreshRequest {

	Long userId;
	String refreshToken;
}