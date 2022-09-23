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
//	@Query("SELECT p FROM DatabaseFile p WHERE (p.postid) = : postid ")
	@Query("SELECT p FROM DatabaseFile p")
	Collection<DatabaseFile> getFilesByPostId(
			@Param("postid")Long post_id);
}

