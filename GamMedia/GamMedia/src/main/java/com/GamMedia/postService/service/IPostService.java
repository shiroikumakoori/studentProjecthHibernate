package com.GamMedia.postService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.GamMedia.postService.entity.Post;




public interface IPostService {
	Post createPost(Post obj);
	Post updatePost(Post obj,Long id);
	Post getbyId(Long id);
	void deletePost(Long id);
	
	List<Post> getAllPost();
	Post updatePost(Post post);
}
