package com.dasc.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
	public Page<User> findAll(Pageable pageable) {

		return userRepository.findAll(pageable);
	}

	@Override
	public User findById(Integer id) {
		Optional<User> op = userRepository.findById(id);
		return op.isPresent() ? op.get() : new User();
	}

	@Override
	public User save(User user) {

		return userRepository.save(user);
	}

	@Override
	public User update(User user) {

		return userRepository.save(user);
	}

	@Override
	public void delete(Integer id) {
		userRepository.deleteById(id);

	}

}
