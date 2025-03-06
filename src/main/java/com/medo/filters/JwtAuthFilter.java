package com.medo.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;



@Component
public class JwtAuthFilter extends OncePerRequestFilter{
	
	@Autowired
private JwtTokenProvider jwtTokenProvider;

// @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        String token = getJwtFromRequest(request);
//        if (token != null && jwtTokenProvider.validateToken(token)) {
//            String mobileNumber = jwtTokenProvider.getUsernameFromToken(token);
//            //added line 30
//            if (mobileNumber != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(mobileNumber, null, null);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//        }
//        
//        filterChain.doFilter(request, response);
//    }
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {
	    try {
	        String token = getJwtFromRequest(request);
	        
	        if (token != null && jwtTokenProvider.validateToken(token)) {
	            String mobileNumber = jwtTokenProvider.getUsernameFromToken(token);
	            
	            if (mobileNumber != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	                UsernamePasswordAuthenticationToken authentication =
	                        new UsernamePasswordAuthenticationToken(mobileNumber, null, null);
	                SecurityContextHolder.getContext().setAuthentication(authentication);
	            }
	        }
	    }
	    catch (ExpiredJwtException ex) {
	        // Handle expired token by sending a 401 response
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.setContentType("application/json");
	        response.getWriter().write("{\"error\": \"Access token expired. Please refresh your token.\"}");
	        return;
	    } catch (JwtException | IllegalArgumentException ex) {
	        // Handle invalid/malformed token cases
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.setContentType("application/json");
	        response.getWriter().write("{\"error\": \"Invalid or missing token.\"}");
	        return;
	    }

	    filterChain.doFilter(request, response);
	}

 
 private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
 }

}
