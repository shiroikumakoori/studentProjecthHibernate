package com.GamMedia.userService.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.GamMedia.payload.USerPaswordDTO;
import com.GamMedia.payload.UserSessionDTO;
import com.GamMedia.userService.entity.User;
import com.GamMedia.userService.service.IUserService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("userService")
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private ModelMapper  mMapper; 
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public User saveUser(@RequestBody User user) {
        //log.info("Inside saveUser of UserController");
        System.out.println("Inside saveUser of UserController");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.createUserAccount(user);
    }
    
 
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        //log.info("Inside saveUser of UserController");
        System.out.println("Inside saveUser of UserController");
         userService.deletePost(id);
    }
    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        //log.info("Inside saveUser of UserController");
        System.out.println("Inside saveUser of UserController");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.updateUserAccount(user);
    }
    
    
    @PutMapping("/update/name")
    public User updateUser(@RequestBody UserSessionDTO user) {
        //log.info("Inside saveUser of UserController");
        System.out.println("Inside saveUser of UserController");
        User u = userService.getUserById(user.getId());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        return userService.updateUserAccount(u);
    }
    @PutMapping("/update/changePassword")
    public String updateUserChangePassword(@RequestBody USerPaswordDTO user ) {
        //log.info("Inside saveUser of UserController");
    	User u = userService.getUserById(user.getId());
		boolean match = passwordEncoder.matches(user.getPassword(), u.getPassword());
      
        if(match) {

        	u.setPassword( passwordEncoder.encode( user.getNewPassword()));
        	userService.updateUserAccount(u);
        	  System.out.println("change password");
        	return "okay changed ";
        }
        else
        {
        	  System.out.println("old password is wrong ");
        	return "old password wrong";
        }
        
    }
    
    
    @GetMapping("/all")
    public List<UserSessionDTO> getAllUser() {
        //log.info("Inside saveUser of UserController");
        System.out.println("get all user");
        List<UserSessionDTO> l = new ArrayList();
        
       for (User u: userService.getAll()) {
    	   
    	   UserSessionDTO dto = new UserSessionDTO();
    	   dto.setFirstName(u.getFirstName());
    	   dto.setLastName(u.getLastName());
    	   dto.setId(u.getId());
    	   l.add(dto);
       } 
        
        return l;
    }

    @GetMapping("/{id}")
    public User getUserByID(@PathVariable("id") Long userId) {
        //log.info("Inside getUserWithDepartment of UserController");
        System.out.println("Inside getUserWithDepartment of UserController");
        User u=  userService.getUserById(userId);
        u.setPassword("");
        return u;
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
