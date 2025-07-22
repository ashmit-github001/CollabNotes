package com.ashmitagarwal.collabnotes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashmitagarwal.collabnotes.user.model.UserRegistrationDto;
import com.ashmitagarwal.collabnotes.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService UserService;
	
	
	public UserController(UserService userService) {
		this.UserService = userService;
	}

	@PostMapping("/register")
	public void registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
		UserService.saveUser(userRegistrationDto);
	}
	
	@GetMapping("/search")
	public void getUserByEmail(@RequestParam("email") String email) {
		
	}


}
