package com.ImtihanPortal.Service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.ImtihanPortal.model.User;
import com.ImtihanPortal.model.UserRole;

@Service
public interface UserService {

		public User createUser(User user, Set<UserRole> userRoles) throws Exception;
		
		public User getUser(String username);
		
		public void deleteUser(Long userId);
}
