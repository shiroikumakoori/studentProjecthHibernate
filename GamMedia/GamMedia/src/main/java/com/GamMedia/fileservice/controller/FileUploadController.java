package com.GamMedia.fileservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.GamMedia.fileservice.entity.DatabaseFile;
import com.GamMedia.fileservice.entity.Response;
import com.GamMedia.fileservice.service.IDatabaseFileService;


@RestController
public class FileUploadController {
	
	@Autowired
	private IDatabaseFileService fileStorageService;
	
	@PostMapping("/uploadFile")
	public Response uploadFile(@RequestParam("file")MultipartFile file) {
		DatabaseFile fileName=fileStorageService.storeFile(file);
		
		String fileDownloadUri=ServletUriComponentsBuilder.fromCurrentContextPath()
				               .path("/downloadFile/")
				               .path(fileName.getFileName())
				               .toUriString();
		return new Response(fileName.getFileName(), fileDownloadUri,file.getContentType(),file.getSize());
	}
}
