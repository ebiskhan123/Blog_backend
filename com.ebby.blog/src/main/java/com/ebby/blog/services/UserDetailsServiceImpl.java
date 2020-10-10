package com.ebby.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebby.blog.dataobjects.UserDTO;
import com.ebby.blog.repository.UserRepository;
import com.ebby.blog.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired 
	UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDTO user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No user found for the user name : " + username  ));
		return UserDetailsImpl.build(user);
	}

}
