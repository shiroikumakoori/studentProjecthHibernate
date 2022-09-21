package com.GamMedia.fileservice.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.GamMedia.fileservice.entity.DatabaseFile;
import com.GamMedia.fileservice.exception.FileNotFoundException;
import com.GamMedia.fileservice.exception.FileStorageException;
import com.GamMedia.fileservice.repository.DatabaseFileRepo;



@Service
public interface IDatabaseFileService {


	
	public DatabaseFile storeFile(MultipartFile file) ;
	public DatabaseFile storeFile(DatabaseFile file);
	
	public DatabaseFile getFile(String fileId); 
}
