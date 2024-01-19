package com.ImtihanPortal.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ImtihanPortal.Service.UserService;
import com.ImtihanPortal.model.User;
import com.ImtihanPortal.model.UserRole;
import com.ImtihanPortal.repo.RoleRepo;
import com.ImtihanPortal.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

		@Autowired
		private UserRepo userRepo;
		
		@Autowired
		private RoleRepo roleRepo;
		
		
	//creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		// TODO Auto-generated method stubs
	 User local = this.userRepo.findByUsername(user.getUsername());
	 
	 if(local != null)
	 {
		 System.out.println("User is already there !!");
		 throw new Exception("User alreday Present !!");
	 }
	 else
	 {
		 		for(UserRole ur:userRoles)
		 		{
		 			roleRepo.save(ur.getRole());
		 		}
		 		
		 		user.getUserRoles().addAll(userRoles);
		 		local = this.userRepo.save(user);
		 
	 }
		
		
		return local;
	}



	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return this.userRepo.findByUsername(username);
	}



	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		 this.userRepo.deleteById(userId);
	}

}
