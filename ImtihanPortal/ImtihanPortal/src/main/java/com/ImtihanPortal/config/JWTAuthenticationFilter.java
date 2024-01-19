package com.ImtihanPortal.config;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ImtihanPortal.impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtil Jwtutil;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

  

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	     final String requestToken = request.getHeader(AUTHORIZATION_HEADER);

	        if (requestToken != null && requestToken.startsWith(BEARER_PREFIX)) {
	            String jwtToken = requestToken.substring(BEARER_PREFIX.length());

	            try {
	                String username = Jwtutil.extractUsername(jwtToken);

	                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	                    UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

	                    if (Jwtutil.validateToken(jwtToken, userDetails)) {
	                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
	                                userDetails, null, userDetails.getAuthorities());

	                        authenticationToken
	                                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	                    }
	                }
	            } catch (ExpiredJwtException e) {
	                logger.error("JWT token has expired", e);
	                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	                response.getWriter().write("Unauthorized: " + e.getMessage());
	                return;
	            } catch (Exception e) {
	                logger.error("Error processing JWT token", e);
	                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	                response.getWriter().write("Unauthorized: " + e.getMessage());
	                return;
	            }
	        } else {
	            logger.info("Invalid token, does not start with Bearer");
	        }

	        filterChain.doFilter(request, response);
	    }
		
	}
