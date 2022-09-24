package com.GamMedia.fileservice.service;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.GamMedia.fileservice.entity.DatabaseFile;
import com.GamMedia.fileservice.exception.FileNotFoundException;
import com.GamMedia.fileservice.exception.FileStorageException;
import com.GamMedia.fileservice.repository.DatabaseFileRepo;

@Service
public class DatabaseFileServiceImpl implements IDatabaseFileService {

	@Autowired
	private DatabaseFileRepo dbFileRespository;
	
	public DatabaseFile storeFile(DatabaseFile file) {

		    return dbFileRespository.save(file);		
	}
	
	public DatabaseFile getFile(Long fileId) {
		return dbFileRespository.findById(fileId)
				.orElseThrow(
						()->new FileNotFoundException(
								"File Not found with ID"+fileId
								)
						);
	}

	@Override
	public DatabaseFile storeFile(MultipartFile file) {
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if(fileName.contains("..")) {
				throw new FileStorageException(
						"Sorry! FileName contains invalid path Sequence"+fileName
						);
			}
			DatabaseFile dbFile=new DatabaseFile(fileName,file.getContentType(),file.getBytes());
	
		    return dbFileRespository.save(dbFile);
		}catch(IOException e) {
			throw new FileStorageException("Could not Store File"+fileName+"please try again",e);
		}
	}

	@Override
	public Collection<DatabaseFile> getFilesByPostId(Long postId) {
		System.out.println(postId);
		return dbFileRespository.getFilesByPostId(postId);
	}


}
