package com.GamMedia.userService.service;

import com.GamMedia.userService.entity.User;

public interface IUserService {
	
	public User createUserAccount( User user);
	public User getUserById(Long id);
	
	public User updateUserAccount(User user, Long id);

}
