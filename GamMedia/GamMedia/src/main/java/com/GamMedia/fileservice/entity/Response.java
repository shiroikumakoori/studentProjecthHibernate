package com.GamMedia.fileservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
	private String fileName;
	private String fileDownload;
	private String fileType;
	private long size;
}
