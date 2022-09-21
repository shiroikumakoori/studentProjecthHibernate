package com.GamMedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GamMedia.postService.service.IPostService;
import com.GamMedia.userService.service.IUserService;

@RestController
@RequestMapping("Home")
public class HomePageController {

	@Autowired
	private IPostService postService;
	

	
}
