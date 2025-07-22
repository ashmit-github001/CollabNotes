package com.ashmitagarwal.collabnotes.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ashmitagarwal.collabnotes.entity.User;
import com.ashmitagarwal.collabnotes.repository.UserRepository;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;
	
	public CustomUserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User userFromDB = userRepository.findByEmail(email);
		
		if(userFromDB == null) {
			System.out.println("No such user exists");
			return null;
		}
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		
		org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User
				(email, userFromDB.getPassword(), true, true, true, true, authorities);
		return user;
	}

}
