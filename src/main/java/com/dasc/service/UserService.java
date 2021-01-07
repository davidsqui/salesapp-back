package com.dasc.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dasc.model.User;

public interface UserService {

	Page<User> findAll(Pageable pageable);

	User findById(Integer id);

	User save(User user);

	User update(User user);

	void delete(Integer id);

}
