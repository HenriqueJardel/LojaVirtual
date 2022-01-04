package com.jt.springboot2backend.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.springboot2backend.dto.CredenciasDTO;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager am;

    private JWTutil jwtu;

    public JWTAuthenticationFilter(AuthenticationManager am, JWTutil jwtu) {
        this.am = am;
        this.jwtu = jwtu;
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
        try {
            CredenciasDTO creds = new ObjectMapper().readValue(req.getInputStream(), CredenciasDTO.class);
	        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getSenha(), new ArrayList<>());
	        Authentication auth = am.authenticate(authToken);
	        return auth;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		} 
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,Authentication auth) {
        String username = ((UserSS) auth.getPrincipal()).getUsername();
        String token = jwtu.generateToken(username);
        res.addHeader("Authorization", "Bearer " + token);
        res.addHeader("access-control-expose-headers", "Authorization");
    }

    private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
        
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                throws IOException, ServletException {
            response.setStatus(401);
            response.setContentType("application/json"); 
            response.getWriter().append(json());
        }
        
        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Não autorizado\", "
                + "\"message\": \"Email ou senha inválidos\", "
                + "\"path\": \"/login\"}";
        }
    }
}