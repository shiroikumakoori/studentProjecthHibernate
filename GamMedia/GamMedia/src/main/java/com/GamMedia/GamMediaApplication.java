package com.GamMedia;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GamMedia.userService.service.SpringSecurityAuditorAware;

@SpringBootApplication
@RestController
@EnableJpaAuditing(auditorAwareRef="auditorAware")
public class GamMediaApplication {

	@Bean
	public ModelMapper modelMapper() {
	 return new ModelMapper(); 
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder(); 
	}
	@Bean 
	public AuditorAware<String> auditorAware()
	{
		return new SpringSecurityAuditorAware();
	}
	
	
	  @RequestMapping("/resource")
	  public Map<String,Object> home() {
	    Map<String,Object> model = new HashMap<String,Object>();
	    model.put("id", UUID.randomUUID().toString());
	    model.put("content", "Hello World");
	    return model;
	  }

	public static void main(String[] args) {
		SpringApplication.run(GamMediaApplication.class, args);
	}

}
