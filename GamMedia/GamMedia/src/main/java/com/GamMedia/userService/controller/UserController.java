package com.GamMedia.userService.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.GamMedia.userService.entity.User;
import com.GamMedia.userService.service.IUserService;


@RestControllerAdvice
@RequestMapping("userService")
public class UserController {
	
	@Autowired
	private IUserService userService;
	

    @PostMapping("/create")
    public User saveUser(@RequestBody User user) {
        //log.info("Inside saveUser of UserController");
        System.out.println("Inside saveUser of UserController");
        return userService.createUserAccount(user);
    }

    @GetMapping("/{id}")
    public User getUserByID(@PathVariable("id") Long userId) {
        //log.info("Inside getUserWithDepartment of UserController");
        System.out.println("Inside getUserWithDepartment of UserController");
        return userService.getUserById(userId);
    }
	
//	  @PostMapping("/create")
//	    public User saveUser(@RequestBody User user) {
//	        //log.info("Inside saveUser of UserController");
//	        System.out.println("Inside saveUser of UserController");
//	        return userService.saveUser(user);
//	    }
//
//	    @GetMapping("/{id}")
//	    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId) {
//	        //log.info("Inside getUserWithDepartment of UserController");
//	        System.out.println("Inside getUserWithDepartment of UserController");
//	        return userService.getUserWithDepartment(userId);
//	    }

	
//	//create blog post 
//	@PostMapping
//	public ResponseEntity<PostDTO> creatPost(@RequestBody PostDTO postDto)
//	{
//		return new ResponseEntity<PostDTO>(postService.createPost(postDto), HttpStatus.CREATED);
//	}
//	
//	
//	//Get all Post  rest APi
//	public PostResponse getAllBlogs(
//			@RequestParam(value="pageNo",defaultValue = AppConst.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
//			@RequestParam(value="pageSize",defaultValue = AppConst.DEFAULT_PAGE_SIZE, required = false)int pageSize,
//			@RequestParam(value="sortBy", defaultValue =  AppConst.DEFAULT_SORT_BY , required= false)String sortBy,
//			@RequestParam(value="sortDir",defaultValue = AppConst.DEFAULT_SORT_DIRECTION, required = false) String sortDir
//			)
//	{
//		return postService.getAppPosts(pageNo, pageSize, sortBy, sortDir);
//	}
//	
//	//Get all Post by id 
//	@GetMapping("/{id}")
//	public ResponseEntity<PostDTO> getPostById(@PathVariable(name="id") long id ){
//		return ResponseEntity.ok(postService.getPostById(id));
//	
//	}
//	
//	//update post by ID
//	@PutMapping("/{id}")
//	public ResponseEntity<PostDTO> updatePost(
//			@RequestBody PostDTO postDto ,@PathVariable(name="id") long id
//			)
//	{
//		PostDTO postResponse =postService.updatePost(postDto,id);
//		return new ResponseEntity<PostDTO>(postResponse, HttpStatus.OK);
//	}
//	
//	@DeleteMapping
//	public ResponseEntity<String> deletePostById(@PathVariable (name="id") long id)
//	{
//		postService.deletPostById(id);
//		return new ResponseEntity<String>("post  deleted sucessful ", HttpStatus.OK);
//	}
	
}