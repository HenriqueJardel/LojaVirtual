package com.jt.springboot2backend.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    
    private JWTutil jwtu;
    
    private UserDetailsService service;

    public JWTAuthorizationFilter(AuthenticationManager am, JWTutil jwtu, UserDetailsService service) {
        super(am);
        this.jwtu = jwtu;
        this.service = service;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            UsernamePasswordAuthenticationToken auth = getAuthentication(request, header.substring(7));
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
	}

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, String token) {
        if(jwtu.tokenValido(token)) {
            String username = jwtu.getUsername(token);
            UserDetails user = service.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        }
        return null;
    }
}
