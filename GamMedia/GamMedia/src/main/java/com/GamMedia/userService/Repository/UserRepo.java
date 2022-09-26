package com.GamMedia.userService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.GamMedia.userService.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	@Query("SELECT p FROM User p WHERE (p.userName) = :userName AND"
			+ " (p.password) = :password ")
	User getUserByUserNamePassword(
			@Param("userName")String userName,
			@Param("password") String password);
	
	@Query("SELECT p FROM User p WHERE (p.userName) = :userName")
	User getUserByUserName(
			@Param("userName")String userName);
	
	@Query("SELECT p FROM User p WHERE(p.email)= :email")
	User getUserByEmail(
			@Param("email")String emali);
}

