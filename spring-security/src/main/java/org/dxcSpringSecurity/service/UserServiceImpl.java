package org.dxcSpringSecurity.service;


import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.dxcSpringSecurity.Repository.UserRepo;
import org.dxcSpringSecurity.dto.UserRegDTO;
import org.dxcSpringSecurity.entity.Role;
import org.dxcSpringSecurity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {


	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	
	
	public UserServiceImpl(UserRepo repo) {
		// TODO Auto-generated constructor stub
		super();
		this.userRepo = repo;
	}
	@Override
	public User save(UserRegDTO rDTO) {

		
		User user = new User();
		user.setEmail(rDTO.getEmail());
		user.setFirstName(rDTO.getFirstName());
		user.setLastName(rDTO.getLastName());
		
		user.setPassword( passwordEncoder.encode( rDTO.getPassword()) );

		user.setRoles( Arrays.asList(new Role("ROLE_USER") ) );
		return userRepo.save(user) ;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userRepo.findByEmail(username);
		if(user ==null)
		{
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new  org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), null) ;
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
