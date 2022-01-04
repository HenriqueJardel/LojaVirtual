package com.jt.springboot2backend.resources;

import java.util.List;

import com.jt.springboot2backend.domain.Estado;
import com.jt.springboot2backend.services.EstadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {
    
    @Autowired
    private EstadoService service;

    @RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Estado>> findAllCidade() {
		List<Estado> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
