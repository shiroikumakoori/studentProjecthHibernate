package com.GamMedia.payload;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
	private Long id;
	private String title;
	private String message; 
	private String urlEmbed;
	private Long views;
	
	private Date lastModifiedDate;
	
	protected Date createdDate;
	
	
	
	private Long fileId;
	private byte[] fileByte;
	private String fileType;
	



	
	
	private Long userId;
	
	
}
