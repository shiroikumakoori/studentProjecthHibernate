package com.GamMedia.postService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GamMedia.postService.entity.Post;
import com.GamMedia.postService.repository.PostRepo;

@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	PostRepo postRepo;
	
	@Override
	public Post createPost(Post id) {

		return  postRepo.save(id);
	}

	@Override
	public Post updatePost(Post post,Long id) {
		
		return null ;
	}
	@Override
	public Post updatePost(Post post) {
		return postRepo.save(post);
	}

	@Override
	public Post getbyId(Long id) {
	
		return postRepo.getReferenceById(id);
	}

	@Override
	public List<Post> getAllPost() {
		// TODO Auto-generated method stub
		
		return postRepo.findAll() ;
	}

	@Override
	public void deletePost(Long id) {
		postRepo.delete(postRepo.getReferenceById(id));
		// TODO Auto-generated method stub
		
	}
	


}
