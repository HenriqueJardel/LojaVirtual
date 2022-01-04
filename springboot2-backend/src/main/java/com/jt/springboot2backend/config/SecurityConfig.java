package com.jt.springboot2backend.config;

import com.jt.springboot2backend.security.JWTAuthenticationFilter;
import com.jt.springboot2backend.security.JWTAuthorizationFilter;
import com.jt.springboot2backend.security.JWTutil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    private static final String [] PUBLIC_GETS = {
        "/produtos/**",
        "/categorias/**",
        "/estados/**"
    };

    private static final String [] PUBLIC_POST = {
        "/clientes/**"
    };

    @Autowired
    private UserDetailsService uds;

    @Autowired
    private JWTutil jwtu;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests()
        .antMatchers(HttpMethod.GET, PUBLIC_GETS).permitAll()
        .antMatchers(HttpMethod.POST, PUBLIC_POST).permitAll()
        .anyRequest().authenticated();
        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtu));
        http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtu, uds));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(uds).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
