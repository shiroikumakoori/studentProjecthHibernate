package com.GamMedia.userService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GamMedia.userService.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}

