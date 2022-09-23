package com.GamMedia.postService.entity;

import com.GamMedia.fileservice.entity.*;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.transaction.annotation.Transactional;

import com.GamMedia.userService.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id ;
	private String title;
	private String message; 
	
//	@ManyToOne(targetEntity=User.class )
//	
//	private User user;
	  @ManyToOne(fetch = FetchType.EAGER, optional = false)
	  @JoinColumn(name = "user_id", nullable = false)
	  @OnDelete(action = OnDeleteAction.CASCADE)
	  private User user;
	  
	  @OneToMany(fetch = FetchType.EAGER, mappedBy ="post" )
	  @OnDelete(action = OnDeleteAction.CASCADE)
	  Collection<DatabaseFile> files;

}
