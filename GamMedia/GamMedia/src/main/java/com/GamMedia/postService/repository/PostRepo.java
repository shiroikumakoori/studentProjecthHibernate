package com.GamMedia.postService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GamMedia.postService.entity.Post;
import com.GamMedia.userService.entity.Role;

public interface PostRepo extends JpaRepository<Post,Long> {

}
