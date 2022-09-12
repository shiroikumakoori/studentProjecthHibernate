package org.dxc.PostController;

import org.dxc.Payload.PostDTO;
import org.dxc.Payload.PostResponse;
import org.dxc.Service.PostService;
import org.dxc.Service.PostServiceInterface;
import org.dxc.utils.AppConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//http://localhost 8080/api/posts
@RequestMapping("api/posts")
public class PostController {
	
	@Autowired
	private PostServiceInterface postService;
	
	//create blog post 
	@PostMapping
	public ResponseEntity<PostDTO> creatPost(@RequestBody PostDTO postDto)
	{
		return new ResponseEntity<PostDTO>(postService.createPost(postDto), HttpStatus.CREATED);
	}
	
	
	//Get all Post  rest APi
	public PostResponse getAllBlogs(
			@RequestParam(value="pageNo",defaultValue = AppConst.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
			@RequestParam(value="pageSize",defaultValue = AppConst.DEFAULT_PAGE_SIZE, required = false)int pageSize,
			@RequestParam(value="sortBy", defaultValue =  AppConst.DEFAULT_SORT_BY , required= false)String sortBy,
			@RequestParam(value="sortDir",defaultValue = AppConst.DEFAULT_SORT_DIRECTION, required = false) String sortDir
			)
	{
		return postService.getAppPosts(pageNo, pageSize, sortBy, sortDir);
	}
	
	//Get all Post by id 
	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable(name="id") long id ){
		return ResponseEntity.ok(postService.getPostById(id));
	
	}
	
	//update post by ID
	@PutMapping("/{id}")
	public ResponseEntity<PostDTO> updatePost(
			@RequestBody PostDTO postDto ,@PathVariable(name="id") long id
			)
	{
		PostDTO postResponse =postService.updatePost(postDto,id);
		return new ResponseEntity<PostDTO>(postResponse, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deletePostById(@PathVariable (name="id") long id)
	{
		postService.deletPostById(id);
		return new ResponseEntity<String>("post  deleted sucessful ", HttpStatus.OK);
	}
	
	
}