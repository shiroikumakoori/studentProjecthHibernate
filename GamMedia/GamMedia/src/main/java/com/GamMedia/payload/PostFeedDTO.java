package com.GamMedia.payload;

import java.util.Collection;
import java.util.Date;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostFeedDTO {

	private Long id;
	private String lastName;
	private String firstName;
	private Long views;
	
	private String title;
	private String message; 
	//private  ResponseEntity<Resource> resource;
	private Long fileId;
	private byte[] fileByte;
	private String fileType;
	
	private String urlEmbed;
	private Long userId;
	private Date lastModifiedDate;
	
	protected Date createdDate;
	
}
