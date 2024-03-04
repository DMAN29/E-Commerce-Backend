package com.ec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.config.JwtProvider;
import com.ec.exception.UserException;
import com.ec.model.User;
import com.ec.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepositoy;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	
	@Override
	public User findUserById(long userId) throws UserException {
		Optional<User> user = userRepositoy.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		throw new UserException("User not found with id : "+userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		String email = jwtProvider.getEmailFromToken(jwt);
		
		User user = userRepositoy.findByEmail(email);
		
		if(user == null) {
			throw new UserException("User Not found with Email : "+email );
		}
		return user;
	}

	//get all users in the admin dashboard
	@Override
	public List<User> findAll() throws UserException {
		// TODO Auto-generated method stub
		return userRepositoy.findAll();
	}

}
