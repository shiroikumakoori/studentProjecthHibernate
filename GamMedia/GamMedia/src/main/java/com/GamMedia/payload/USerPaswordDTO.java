package com.GamMedia.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class USerPaswordDTO {
	private Long id;
	

	
	private String password;
	private String newPassword;
}
