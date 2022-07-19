package com.project.login.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.login.entity.User;
import com.project.login.exception.UserNotFoundException;
import com.project.login.response.UserResponse;
import com.project.login.service.UserService;

@RestController
@RequestMapping("/users") //ana path adi
public class UserController {
	
	private UserService userService;
	
	//constructor injection
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	// tum userlari donen controller 
	@GetMapping // Bir seyleri getirmek icin
	public List<User> getAllUsers(){
		return userService.geltAllUsers();
	}
	
	@PostMapping //bir seyleri create etmek icin
	public User createUser(@RequestBody User newUser) { //RequestBody'deki bilgileri alip user objesine maple 
		return userService.saveOneUser(newUser); // user objesi db'ye save edilir
	}
	

	
	// 404 not found donmesi icin
	// olmayan bir user istendiginde boyle bir user yok bunun icin custom exception
	@GetMapping("/{userId}") // userId ile sipesifik bir user gelir
	public UserResponse getOneUser(@PathVariable Long userId) {
		User user = userService.getOneUserById(userId);
		if(user == null) { 
			throw new UserNotFoundException();
		}
		return new UserResponse(user);
	}
	
	@PutMapping("/{userId}") // var olan bir Id'li user uzerinde degisiklik yapabiliriz
	public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
		return userService.updateOneUser(userId, newUser);
	}
	
	@DeleteMapping("/{userId}") // birisi sipesifik bir user icin delete istegi attiginda
	public void deleteOneUser(@PathVariable Long userId) {
		userService.deleteById(userId);
	}
	
//	@GetMapping("/activity/{userId}")
//	public List<Object> getUserActivity(@PathVariable Long userId) {
//		return userService.getUserActivity(userId);
//	}
	
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private void handleUserNotFound() {
		
	}
	

}
