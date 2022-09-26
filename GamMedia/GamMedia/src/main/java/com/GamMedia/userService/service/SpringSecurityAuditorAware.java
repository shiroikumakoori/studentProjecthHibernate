package com.GamMedia.userService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.Data;

@Data
public class SpringSecurityAuditorAware implements AuditorAware<String>{

	@Autowired
	private IUserService userService;
	
	@Override
	public Optional<String> getCurrentAuditor() {

		Optional<String> s = Optional.ofNullable(userService.getCurrLoginUser());
		    return s;
	}

}
