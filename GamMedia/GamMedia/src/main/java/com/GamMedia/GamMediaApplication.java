package com.GamMedia;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GamMediaApplication {

	@Bean
	public ModelMapper modelMapper() {
	 return new ModelMapper();
	}
//	@Bean
//	public MyLogger logger()
//	{
//		return new MyLogger();
//	}
	public static void main(String[] args) {
		SpringApplication.run(GamMediaApplication.class, args);
	}

}
