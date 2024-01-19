package com.ImtihanPortal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ImtihanPortal.model.User;
import com.ImtihanPortal.repo.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
			
		User user = this.userRepo.findByUsername(username);
		
		if(user==null)
		{
			System.out.println("user not found");
			throw new UsernameNotFoundException("no user found");
		}
			
		
		return user;
	}

}
