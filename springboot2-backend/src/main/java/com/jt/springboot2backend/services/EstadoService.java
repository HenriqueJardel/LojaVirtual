package com.jt.springboot2backend.services;

import java.util.List;

import com.jt.springboot2backend.domain.Estado;
import com.jt.springboot2backend.repositories.EstadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {
    
    @Autowired
    private EstadoRepository estadoRepository;
    

    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }
}
