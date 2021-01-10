package com.dasc.service.impl;

import org.springframework.stereotype.Service;

import com.dasc.exception.ApiException;
import com.dasc.model.User;
import com.dasc.repository.UserRepository;
import com.dasc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User logIn(String email, String password) {

		User user = userRepository.findByEmailAndPassword(email, password);

		if (user == null) {
			throw new ApiException("Credenciales incorrectas, vuelva a intentarlo");
		}

		return user;
	}

}
