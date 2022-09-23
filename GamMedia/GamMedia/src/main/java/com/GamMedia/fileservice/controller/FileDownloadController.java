package com.GamMedia.fileservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.GamMedia.fileservice.entity.DatabaseFile;
import com.GamMedia.fileservice.service.IDatabaseFileService;

@RestController
public class FileDownloadController {

	
	@Autowired
	private IDatabaseFileService fileService;
	
	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long fileName,HttpServletRequest request){
		
		DatabaseFile databaseFile = fileService.getFile(fileName);
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(databaseFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+databaseFile.getFileName()+"\"")
                .body(new ByteArrayResource(databaseFile.getData()));
	}
}
