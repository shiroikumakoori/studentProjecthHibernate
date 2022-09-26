package com.GamMedia.userService.service;

import java.util.List;

import com.GamMedia.payload.UserSessionDTO;
import com.GamMedia.userService.entity.User;

public interface IUserService {
	

	public String getCurrLoginUser();
	public User createUserAccount( User user);
	public UserSessionDTO getUserDTOById(Long id);
	public User getUserById(Long id);
	
	public User getUserByUserNamePassword(String userName,String password); 
	public User getUserByUserName(String userName); 
	public User updateUserAccount(User user);
	public UserSessionDTO updateUserAccount(UserSessionDTO user);
	
	public void deletePost(Long id);
	
	public User updateUserAccount(Long id);
	void deletePost(Long userId, Long Postid);
	
	 User getUserByEmail(String email);
	 
	 public List<User> getAll();
	void setCurrLoginUser(User user);

}
