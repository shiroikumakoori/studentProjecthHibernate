package com.GamMedia.payload;

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
	
	private Long userId;
}
