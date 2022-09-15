package org.dxcSpringSecurity.service;


import org.dxcSpringSecurity.dto.UserRegDTO;
import org.dxcSpringSecurity.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface IUserService extends UserDetailsService {
	User save(UserRegDTO registerationDTO);
}
