package com.jt.springboot2backend.services;

import com.jt.springboot2backend.domain.Cliente;
import com.jt.springboot2backend.repositories.ClienteRepository;
import com.jt.springboot2backend.security.UserSS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente obj = clienteRepository.findByEmail(username);

        if(obj == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserSS(obj.getId(),obj.getEmail(),obj.getSenha(),obj.getPerfis());
    }
    
}
