package com.GamMedia.userService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GamMedia.userService.Repository.UserRepo;
import com.GamMedia.userService.entity.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public User createUserAccount(User user) {

	    System.out.println(" UserServiceImpl:createUserAccount(): create new user Account");
		return userRepo.save(user);
	}

	@Override
	public User updateUserAccount(User user, Long id) {
		// TODO Auto-generated method stub
	    System.out.println(" UserServiceImpl:updateUserAccount(): updating user id: " + id);
		return null;
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepo.getReferenceById(id);
	}

}
