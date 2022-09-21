package com.GamMedia.payload;

import java.util.Collection;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostFeedDTO {

	private Long userId;
	
	private String lastName;
	private String firstName;
	
	private String title;
	private String message; 
	private Collection< ResponseEntity<Resource>> resource;
	
}
