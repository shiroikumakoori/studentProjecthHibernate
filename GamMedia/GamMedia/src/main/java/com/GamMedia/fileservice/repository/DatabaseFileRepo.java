package com.GamMedia.fileservice.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.GamMedia.fileservice.entity.DatabaseFile;
import com.GamMedia.userService.entity.User;

@Repository
public interface DatabaseFileRepo extends JpaRepository<DatabaseFile,Long>{

	@Query("SELECT p FROM DatabaseFile p WHERE (p.post.id) =  ?1 ")
	Collection<DatabaseFile> getFilesByPostId(
			Long postid);
	//Collection<DatabaseFile> getFilesByPostId(Long post_id);
}
