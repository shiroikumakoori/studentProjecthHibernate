package com.GamMedia.userService.entity;

import java.util.Collection;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.GamMedia.postService.entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String lastName;
	private String firstName;
	private String userName;
	private String password;
	
	private String email; 
//	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//
//	 @JoinColumn( name = "role_id", referencedColumnName = "id")
	private String roles;
	
	


}
