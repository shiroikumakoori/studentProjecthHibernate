package com.GamMedia.postService.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.modelmapper.Converters.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("postService" )
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
    public Long saveUser(@RequestBody PostDTO postDTO) {
    	
    	l.logToStream(this.getClass().getName() ,"saveUser()");
    	try {
//	    
				Post post = this.mMapper.map(postDTO, Post.class);
				post.setViews(0l);
				post.setId(null);
		        post.setUser( userService.getUserById(postDTO.getUserId()));
		        Post newPost = postService.createPost(post);
		        l.logToStream(this.getClass().getName(), "saveUser(): postcreated");
		        
		        return newPost.getId() ;
			
    	} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	  return null;
    }

    @GetMapping("/{id}")
    public PostFeedDTO getUserByID(@PathVariable("id") Long id) {
    	l.logToStream(this.getClass().getName() ,"getUserByID(): " + id );
        Post post = postService.getbyId(id);
        if(post != null)
        {
        	
        	PostFeedDTO dto =  mMapper.map(post, PostFeedDTO.class);
        	UserSessionDTO userDto= userService.getUserDTOById( post.getUser().getId());
        	if(dto.getViews()==null) 
				dto.setViews(0l);
			
			dto.setFirstName(userDto.getFirstName());
    		dto.setLastName(userDto.getLastName());
    		 Collection<DatabaseFile> l= fileService.getFilesByPostId(post.getId());
    		 if(l != null)
    		 {
    			 
    			System.out.println("post id:"+ post.getId());
	    		for ( DatabaseFile file : l ) {
	    			//dto.setResource(loadMedia(file));
	    			///dto.setImageUrl("localhost:8080/postService/downloadFile/62059d23-bba5-4243-be16-d23e4b73c0a4");
	    			//dto.getResource().add( loadMedia(file));
	    			System.out.println("file id_postId:"+ file.getId() +" "+ file.getPost().getId());
	    			dto.setFileId(file.getId());
	    			dto.setFileByte(file.getData());
	    			dto.setFileType(file.getFileType());

				} 
    		 }
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
    @PutMapping("/update")
    public void updatePost(@RequestBody PostDTO postDTO)
    {
    	System.out.println("Inside saveUser of UserController");

		if(postDTO.getUserId() != null)
		{
			System.out.println("user has login");
	
			Post post = this.mMapper.map(postDTO, Post.class);
			//post.setId(postDTO.getUserId());
	        //post.setUser( userService.getUserById(uDTO.getUserId()));
			l.logToStream(this.getClass().getName() ,"updatePost(): " + post.getId());
	        postService.updatePost(post);
	       // postService.createPost(post);
		}
		else
		{
			l.logToStream(this.getClass().getName() ,"updatePost(): " +" doesn't exist");
		}
    }
    @PutMapping("/update/{id}")
    public void updatePostById(@RequestBody PostDTO postDTO, HttpSession session,
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
    		//dto.setResource(new ArrayList());
    		 Collection<DatabaseFile> l= fileService.getFilesByPostId(post.getId());
    		 if(l != null)
    		 {
    			 
    			System.out.println("post id:"+ post.getId());
	    		for ( DatabaseFile file : l ) {
	    			//dto.setResource(loadMedia(file));
	    			///dto.setImageUrl("localhost:8080/postService/downloadFile/62059d23-bba5-4243-be16-d23e4b73c0a4");
	    			//dto.getResource().add( loadMedia(file));
	    			System.out.println("file id_postId:"+ file.getId() +" "+ file.getPost().getId());
	    			dto.setFileId(file.getId());
	    			dto.setFileByte(file.getData());
	    			//dto.setFileByte(new ByteArrayResource(file.getData()));
				} 
    		 }
    		//downloadFile()
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
    @SuppressWarnings("unchecked")
	@GetMapping("/homePage/section")
    public Page<PostFeedDTO>  test (@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "1") int size)
    {
    	Pageable paging = PageRequest.of(page, size, Sort.by("createdDate").descending());
    	   	
    	return postService.getPagePost(paging).map( new Function<Post, PostFeedDTO>(){

			@Override
			public PostFeedDTO apply(Post t) {
				UserSessionDTO userDto= userService.getUserDTOById( t.getUser().getId());
				PostFeedDTO dto = mMapper.map(t, PostFeedDTO.class);
				if(dto.getViews()==null) 
					dto.setViews(0l);
				
				dto.setFirstName(userDto.getFirstName());
	    		dto.setLastName(userDto.getLastName());
	    		 Collection<DatabaseFile> l= fileService.getFilesByPostId(t.getId());
	    		 if(l != null)
	    		 {
	    			 
	    			System.out.println("post id:"+ t.getId());
		    		for ( DatabaseFile file : l ) {
		    			//dto.setResource(loadMedia(file));
		    			///dto.setImageUrl("localhost:8080/postService/downloadFile/62059d23-bba5-4243-be16-d23e4b73c0a4");
		    			//dto.getResource().add( loadMedia(file));
		    			System.out.println("file id_postId:"+ file.getId() +" "+ file.getPost().getId());
		    			dto.setFileId(file.getId());
		    			dto.setFileByte(file.getData());
		    			dto.setFileType(file.getFileType());

					} 
	    		 }
				return dto;
			}
    		
    	});

    }
    //Get all Post  rest APi
//	public PostResponse getAllBlogs(
//			@RequestParam(value="pageNo",defaultValue = AppConst.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
//			@RequestParam(value="pageSize",defaultValue = AppConst.DEFAULT_PAGE_SIZE, required = false)int pageSize,
//			@RequestParam(value="sortBy", defaultValue =  AppConst.DEFAULT_SORT_BY , required= false)String sortBy,
//			@RequestParam(value="sortDir",defaultValue = AppConst.DEFAULT_SORT_DIRECTION, required = false) String sortDir
//			)
//	{
//		return postService.getAppPosts(pageNo, pageSize, sortBy, sortDir);
//	}
	
    
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
		//post.getFiles().add(fileName);
		fileName.setPost(post );
		
		//System.out.println(fileName.getPost().getId());
		fileName=		fileService.storeFile(fileName);
		
		String fileDownloadUri=ServletUriComponentsBuilder.fromCurrentContextPath()
				               .path("/downloadFile/")
				               .path(fileName.getFileName())
				               .toUriString();
		return new Response(fileName.getFileName(), fileDownloadUri,file.getContentType(),file.getSize());
	}
	
	
//	@GetMapping("/downloadFile/{id}")
//	public ResponseEntity<Resource> downloadFile(@PathVariable("id") Long id)
//	{
//		return loadMedia(id);
//	}
	
	@GetMapping("/downloadFile/{id}")
	public byte[] downloadFile(@PathVariable("id") Long id)
	{
		return fileService.getFile(id).getData();
	}
//	@GetMapping("/downloadFile/{id}")
//	public String downloadFile(@PathVariable("id") String id)
//	{
//		System.out.println("download");
//		return "okay";
//	}
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
	
	
	private ResponseEntity<Resource> loadMedia(Long fileId)
	{
		DatabaseFile file = fileService.getFile(fileId);
		
		return ResponseEntity.ok()
		.contentType(MediaType.parseMediaType(file.getFileType()))
		.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+file.getFileName()+"\"")
        .body(new ByteArrayResource(file.getData()));
	
	}
	private ResponseEntity<Resource> loadMedia(DatabaseFile file)
	{
		
		return ResponseEntity.ok()
		.contentType(MediaType.parseMediaType(file.getFileType()))
		.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+file.getFileName()+"\"")
        .body(new ByteArrayResource(file.getData()));
	
	}

	private void test()
	{
//	    InputStream in = getClass()
//	    	      .getResourceAsStream("/com/baeldung/produceimage/image.jpg");
//	    IOUtils.toByteArray(in);
	}
	
	
	
}
