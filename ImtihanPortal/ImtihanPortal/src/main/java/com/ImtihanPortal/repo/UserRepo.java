package com.ImtihanPortal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ImtihanPortal.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

		public	User findByUsername(String username);

		

}
