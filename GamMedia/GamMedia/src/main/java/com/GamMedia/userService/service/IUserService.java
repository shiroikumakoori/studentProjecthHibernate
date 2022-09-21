package com.GamMedia.userService.service;

import com.GamMedia.payload.UserSessionDTO;
import com.GamMedia.userService.entity.User;

public interface IUserService {
	
	public User createUserAccount( User user);
	public UserSessionDTO getUserDTOById(Long id);
	public User getUserById(Long id);
	
	public User getUserByUserNamePassword(String userName,String password); 
	public User updateUserAccount(User user);
	public UserSessionDTO updateUserAccount(UserSessionDTO user);
	
	public void deletePost(Long id);
	
	public User updateUserAccount(Long id);
	void deletePost(Long userId, Long Postid);

}
