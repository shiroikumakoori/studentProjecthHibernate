package com.GamMedia.payload;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import com.GamMedia.postService.entity.Post;
import com.GamMedia.userService.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSessionDTO {
	private Long id;
	
	private String firstName;
	private String lastName;
	
	private Set<Role> roles;
	
	private Set<Post> post;
	
	public void addPost(Post p)
	{
		if(post!=null)
		{
			post.add(p);
		}
		else
		{
			post = new HashSet<>();
		}
	}
	
}
