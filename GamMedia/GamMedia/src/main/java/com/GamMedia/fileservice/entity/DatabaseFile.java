package com.GamMedia.fileservice.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.GamMedia.postService.entity.Post;
import com.GamMedia.userService.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="files")
public class DatabaseFile {
	@Id
//	@GeneratedValue(generator = "uuid")
//	@GenericGenerator(name="uuid", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fileName;
	private String fileType;
	
	@Lob
	private byte[] data;
	
	
	 @ManyToOne(fetch = FetchType.EAGER, optional = false )
	  @JoinColumn(name = "postid", nullable = false)
	  @OnDelete(action = OnDeleteAction.CASCADE)
	  private Post post;


	public DatabaseFile(String fileName, String fileType, byte[] data) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}
	
}
