package com.ashmitagarwal.collabnotes.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ashmitagarwal.collabnotes.entity.User;
import com.ashmitagarwal.collabnotes.repository.UserRepository;
import com.ashmitagarwal.collabnotes.user.model.UserRegistrationDto;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public boolean saveUser(UserRegistrationDto userDto) {
		
		User existingUser = userRepository.findByEmail(userDto.getEmail());
		
		if(existingUser == null) {
			User user = new User();
			user.setFullName(userDto.getName());
			user.setEmail(userDto.getEmail());
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
			userRepository.save(user);
			return true;
		}
		
		System.out.println("User with this email already exists");
		return false;
	}

}


