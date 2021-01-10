package com.dasc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dasc.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmailAndPassword(String email, String password);

}
