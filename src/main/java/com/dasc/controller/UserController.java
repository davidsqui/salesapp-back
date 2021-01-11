package com.dasc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dasc.dto.LoginDto;
import com.dasc.model.User;
import com.dasc.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<User> logIn(@RequestBody LoginDto login) {

		User user = userService.logIn(login.getEmail(), login.getPassword());

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
