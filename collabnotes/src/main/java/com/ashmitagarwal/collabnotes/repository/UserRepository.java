package com.ashmitagarwal.collabnotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashmitagarwal.collabnotes.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
