package com.GamMedia.fileservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GamMedia.fileservice.entity.DatabaseFile;

@Repository
public interface DatabaseFileRepo extends JpaRepository<DatabaseFile,String>{

}

