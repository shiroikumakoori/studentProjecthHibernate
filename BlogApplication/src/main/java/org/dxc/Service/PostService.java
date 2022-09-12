package org.dxc.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

import org.dxc.Payload.PostDTO;
import org.dxc.Payload.PostResponse;
import org.dxc.Repository.PostRepository;
import org.dxc.entity.Post;
import org.dxc.execption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

@Service 
public class PostService implements PostServiceInterface {

	@Autowired
	private PostRepository postRepository;
	
	private PostDTO mapToDto(Post post) {
		PostDTO postDto= new PostDTO();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setDescription(post.getDescription());
		postDto.setContent(post.getContent());
		return postDto;
	}
		
	//convert dto to entity
	public Post mapToEntity(PostDTO postDto) {
	    Post post = new Post();
	    post.setId(null);
	    post.setTitle(postDto.getTitle());
	    post.setDescription(postDto.getDescription());
	    post.setContent(postDto.getContent());
		return post;
	}

	//implementing create postblog
	@Override
	public PostDTO createPost(PostDTO postDto) {
		//convert dto to entity
		Post post = mapToEntity(postDto);
		Post newPost= postRepository.save(post);
		PostDTO postResponse = mapToDto(newPost);
		return postResponse;
	}

	//implmenting the get PostBlog
	@Override
	public PostResponse getAppPosts(int pagNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
				Sort.by(sortBy).ascending()
				:Sort.by(sortBy).descending();
		
		
		Pageable pageable =(Pageable) PageRequest.of(pagNo, pageSize,sort);
		Page<Post>posts = (Page<Post>) postRepository.findAll((Sort) pageable);
		
		//get content from page object 
		List<Post> listOfPosts= posts.getContent();
		List<PostDTO> content =listOfPosts.stream().map(
				post ->mapToDto(post)).collect(Collectors.toList()
				);
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElement(posts.getTotalElements() );
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		return postResponse;
	}

	@Override
	public PostDTO getPostById(Long id) {
		Post post =postRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("POST", "id", id)
				);
		
		return mapToDto(post);
	}

	@Override
	@PutMapping("/{id}")
	public PostDTO updatePost(PostDTO postDto, Long id) {
		Post post =postRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("POST", "id", id)
				);
		
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		Post udpatePost = postRepository.save(post);
		
		return mapToDto(udpatePost);
	}

	@Override
	public void deletPostById(long id) {
		Post post =postRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("POST", "id", id)
				);
		postRepository.delete(post);
		
	}
	



}
