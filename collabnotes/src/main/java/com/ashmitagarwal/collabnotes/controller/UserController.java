package com.ashmitagarwal.collabnotes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@PostMapping("/regsiter")
	public void registerUser() {
		
	}
	
	@PostMapping("/login")
	public void login() {
		
	}
	
	@GetMapping("/search")
	public void getUserByEmail(@RequestParam("email") String email) {
		
	}


}
