package com.project.login.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.login.entity.User;
import com.project.login.repository.UserRepository;

@Service
public class UserService {
	
	UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> geltAllUsers() {
		// burada repoya baglanip tum userlari aliriz
		return userRepository.findAll();
	}

	public User saveOneUser(User newUser) {
		return userRepository.save(newUser);
	}

	public User getOneUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	public User updateOneUser(Long userId, User newUser) {
		//değiştirilmek istenen useri userid kullanarak bulup user değişkenine atıyoruz
		Optional<User> user = userRepository.findById(userId);
		//user değişkeni databasede varmı diye kontrol ediyoruz
		if(user.isPresent()) {
			//var ise useri founduser değişkenine atıyoruz
			User foundUser = user.get();
			//varolan useri new userin verdiği değişikliklerle yeniden set ediyoruz
			foundUser.setUsername(newUser.getUsername());
			foundUser.setPassword(newUser.getPassword());
			//useri kaydediyoruz
			userRepository.save(foundUser); // db'ye save ettik
			//ve düzenlenmiş useri döndürüyoruz
			return foundUser;
		}else  // boyle bir user yoksa
			return null;
	}

	public void deleteById(Long userId) {
		userRepository.deleteById(userId);
	}

	public User getOneUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
//Comment like post icin
//	public List<Object> getUserActivity(Long userId) {
//		List<Long> postIds = postRepository.findTopByUserId(userId);
//		if(postIds.isEmpty())
//			return null;
//		List<Object> comments = commentRepository.findUserCommentsByPostId(postIds);
//		List<Object> likes = likeRepository.findUserLikesByPostId(postIds);
//		List<Object> result = new ArrayList<>();
//		result.addAll(comments);
//		result.addAll(likes);
//		return result;
//	}


}
