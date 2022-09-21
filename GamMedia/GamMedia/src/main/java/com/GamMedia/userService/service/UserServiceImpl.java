package com.GamMedia.userService.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GamMedia.payload.UserSessionDTO;

import com.GamMedia.userService.Repository.UserRepo;
import com.GamMedia.userService.entity.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public User createUserAccount(User user) {

	    System.out.println(" UserServiceImpl:createUserAccount(): create new user Account");
		//if (user.getPost()== null) user.setPost(new HashSet<>());
	    return userRepo.save(user);
	}

	@Override
	public User updateUserAccount(User user) {
		// TODO fix ineffiency postCollection setting 
	    System.out.println(" UserServiceImpl:updateUserAccount(): updating " );
	    User old = userRepo.getReferenceById(user.getId());
	   // old.setPost(user.getPost());
	  
		return   userRepo.save(old);
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepo.getReferenceById(id);
	}

	@Override
	public User updateUserAccount(Long id) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public User getUserByUserNamePassword(String userName, String password) {
		
		//userRepo.getUserByUserNamePassword(userName,password);
		return userRepo.getUserByUserNamePassword(userName,password);
	}

	@Override
	public UserSessionDTO updateUserAccount(UserSessionDTO user) {
	    System.out.println(" UserServiceImpl:updateUserAccount(): updating " );
	    User old = userRepo.getReferenceById(user.getId());
	    //old.setPost(user.getPost());
	    userRepo.save(old);
		return user;
	}

	@Override
	public UserSessionDTO getUserDTOById(Long id) {
	    System.out.println(" getUserDTOById():"+id  );
	    UserSessionDTO dto = new UserSessionDTO();
	    User old = userRepo.getReferenceById(id);
	    dto.setFirstName(old.getFirstName());
	    dto.setLastName(old.getLastName());
	    //dto.setPost(old.getPost());
	    dto.setRoles(old.getRoles());
	
		return dto;
	}

	@Override
	public void deletePost(Long userId,Long Postid) {
//		 User old = userRepo.getReferenceById(userId);
//		 old.getPost().remove(postid)
//		    old.setPost(user.getPost());
//		    userRepo.save(old);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePost(Long id) {
		// TODO Auto-generated method stub
		
	}

}
