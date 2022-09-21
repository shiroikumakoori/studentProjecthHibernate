package com.GamMedia;

import org.springframework.stereotype.Service;

@Service
public class MyLogger implements ILogger {

	@Override
	public void logToStream(String printingClass, String functionName) {
		System.out.println(printingClass + " : "+ functionName );
		
	}
	
}
