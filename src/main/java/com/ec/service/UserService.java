package com.ec.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ec.exception.UserException;
import com.ec.model.User;

public interface UserService {
	
	public List<User> findAll() throws UserException;

	public User findUserById(long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
}
