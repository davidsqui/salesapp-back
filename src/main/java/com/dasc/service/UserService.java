package com.dasc.service;

import com.dasc.model.User;

public interface UserService {

	User logIn(String email, String password);

}
