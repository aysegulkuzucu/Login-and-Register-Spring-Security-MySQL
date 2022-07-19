package com.project.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.login.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

	RefreshToken findByUserId(Long userId);
	
}