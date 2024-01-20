package com.ImtihanPortal.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ImtihanPortal.config.JwtUtil;
import com.ImtihanPortal.impl.UserDetailsServiceImpl;
import com.ImtihanPortal.model.JwtRequest;
import com.ImtihanPortal.model.JwtResponse;
import com.ImtihanPortal.model.User;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;
		
		@Autowired
		private UserDetailsServiceImpl userDetailsServiceImpl;
	
		
		@Autowired
		private JwtUtil jwtUtil;
		
		
		@PostMapping("/generate-token")
		public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
		{
			try {
					authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
			}
			catch(UsernameNotFoundException e)
			{
				e.printStackTrace();
				throw new Exception("user not found");
			}

			UserDetails userdetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
			String token = this.jwtUtil.generateToken(userdetails);
			return  ResponseEntity.ok(new JwtResponse(token));
		}
		
			private void authenticate(String username, String password) throws Exception
			{
				try {
						authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
				}
				catch(DisabledException e)
				{
					throw new Exception("User DISABLED"+ e.getMessage());
				}
				catch(BadCredentialsException e)
				{
					throw new Exception("Invalid Credentails" + e.getMessage());
				}
				
			}
			
			@GetMapping("/current-user")
			public User getCurrentUser(Principal principal)
			{
				
				return (User) this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
			
			}
}
