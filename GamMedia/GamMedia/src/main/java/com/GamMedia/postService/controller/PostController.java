package com.GamMedia.postService.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.GamMedia.MyLogger;
import com.GamMedia.fileservice.entity.DatabaseFile;
import com.GamMedia.fileservice.entity.Response;
import com.GamMedia.fileservice.exception.FileStorageException;
import com.GamMedia.fileservice.service.IDatabaseFileService;
import com.GamMedia.payload.PostDTO;
import com.GamMedia.payload.PostFeedDTO;
import com.GamMedia.payload.UserSessionDTO;
import com.GamMedia.postService.entity.Post;
import com.GamMedia.postService.service.IPostService;
import com.GamMedia.userService.entity.User;
import com.GamMedia.userService.service.IUserService;


@RestController
@RequestMapping("postService")
public class PostController {

	String[] attributeNames={ "Curr_LoginUser"};
	String getUserAttName() {return attributeNames[0];}
	
	@Autowired
	private IDatabaseFileService fileService;
	
	@Autowired
	private IPostService postService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ModelMapper  mMapper; 
	@Autowired
	MyLogger l;
	

	
    @PostMapping("/create")
    public String saveUser(@RequestBody PostDTO postDTO, HttpSession session) {
    	
    	l.logToStream(this.getClass().getName() ,"saveUser()");
    	try {
	    	Object obj =session.getAttribute(getUserAttName());
			if(obj!= null)
			{			
				UserSessionDTO uDTO = (UserSessionDTO) obj;
				Post post = this.mMapper.map(postDTO, Post.class);
		
		        post.setUser( userService.getUserById(uDTO.getId()));
		        postService.createPost(post);
		        l.logToStream(this.getClass().getName(), "saveUser(): postcreated");

			}
			else {
				l.logToStream(this.getClass().getName(), "saveUser(): user not login");
			}
    	} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        return "redirect:userService/login/HomePage/";
    }

    @GetMapping("/{id}")
    public PostDTO getUserByID(@PathVariable("id") Long id) {
    	l.logToStream(this.getClass().getName() ,"getUserByID(): " + id );
        Post post = postService.getbyId(id);
        if(post != null)
        {
        	
        	PostDTO dto =  mMapper.map(post, PostDTO.class);
        	return dto;
        }
        else
        {      	
        	return null ;
        }
    }
    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable("id") Long id) {
        //log.info("Inside getUserWithDepartment of UserController");
    	l.logToStream(this.getClass().getName(), "deletePost(): deleting "+ id);
        postService.deletePost(id);
    }
    
    @PutMapping("/edit/{id}")
    public void updatePost(@RequestBody PostDTO postDTO, HttpSession session,
    		@PathVariable("id") Long id)
    {
    	System.out.println("Inside saveUser of UserController");
    	l.logToStream(this.getClass().getName() ,"updatePost(): " + id);
    	Object obj =session.getAttribute(getUserAttName());
		if(obj!= null)
		{
			System.out.println("user has login");
			UserSessionDTO uDTO = (UserSessionDTO) obj;
	
			Post post = this.mMapper.map(postDTO, Post.class);
			post.setId(id);
	        //post.setUser( userService.getUserById(uDTO.getUserId()));
	        postService.updatePost(post);
	       // postService.createPost(post);
		}
		else
		{
			l.logToStream(this.getClass().getName() ,"updatePost(): " +" doesn't exist");
		}
    }
    @GetMapping("/homePage/all")
	public List<PostFeedDTO> loginIn(HttpSession session)
	{

    	List<PostFeedDTO> list = new ArrayList();
    	  	
    	for (Post post : postService.getAllPost()) {
    		
    		PostFeedDTO dto = this.mMapper.map(post, PostFeedDTO.class);
    		UserSessionDTO userDto= userService.getUserDTOById( post.getUser().getId());
    		
    		dto.setFirstName(userDto.getFirstName());
    		dto.setLastName(userDto.getLastName());
    		//TODO doesnt seem to work well with mulit files =.=
//    		Collection< ResponseEntity<Resource>> files= new ArrayList();
//    		for (DatabaseFile file : post.getFiles()) {
//    			files.add( loadMedia(file.getId()));
//			}
//    		dto.setResource(files);
    		list.add(dto);
    	}
				
		return list; 
	}
    
	@PostMapping("/uploadFile")
	public Response uploadFile(@RequestParam("file")MultipartFile file) {
		DatabaseFile fileName=fileService.storeFile(file);
		
		String fileDownloadUri=ServletUriComponentsBuilder.fromCurrentContextPath()
				               .path("/downloadFile/")
				               .path(fileName.getFileName())
				               .toUriString();
		return new Response(fileName.getFileName(), fileDownloadUri,file.getContentType(),file.getSize());
	}
	
	@PostMapping("/uploadFile/{id}")
	public Response uploadFile(@RequestParam("file")MultipartFile file,@PathVariable("id") Long id
			) {
	
	
		Post post = postService.getbyId(id);

		DatabaseFile fileName= conFileToDbFile(file);
		post.getFiles().add(fileName);
		//fileName.setPost(post );
		
		//System.out.println(fileName.getPost().getId());
		fileName=		fileService.storeFile(fileName);
		
		String fileDownloadUri=ServletUriComponentsBuilder.fromCurrentContextPath()
				               .path("/downloadFile/")
				               .path(fileName.getFileName())
				               .toUriString();
		return new Response(fileName.getFileName(), fileDownloadUri,file.getContentType(),file.getSize());
	}
	
	@GetMapping("/downloadFile/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("id") String id)
	{
		return loadMedia(id);
	}
	private DatabaseFile conFileToDbFile(MultipartFile file)
	{
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if(fileName.contains("..")) {
				throw new FileStorageException(
						"Sorry! FileName contains invalid path Sequence"+fileName
						);
			}
			DatabaseFile dbFile=new DatabaseFile(fileName,file.getContentType(),file.getBytes());
	
		    return dbFile;
		}catch(IOException e) {
			throw new FileStorageException("Could not Store File"+fileName+"please try again",e);
		}
	}
	private ResponseEntity<Resource> loadMedia(String fileId)
	{
		DatabaseFile file = fileService.getFile(fileId);
		
		return ResponseEntity.ok()
		.contentType(MediaType.parseMediaType(file.getFileType()))
		.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+file.getFileName()+"\"")
        .body(new ByteArrayResource(file.getData()));
	
	}
	
	
	
}