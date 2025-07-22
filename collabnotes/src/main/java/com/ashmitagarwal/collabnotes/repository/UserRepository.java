package com.ashmitagarwal.collabnotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ashmitagarwal.collabnotes.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	@Query("Select u from User u where u.email = :email")
	User findByEmail(String email);
}
