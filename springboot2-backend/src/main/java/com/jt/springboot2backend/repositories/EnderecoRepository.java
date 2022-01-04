package com.jt.springboot2backend.repositories;

import com.jt.springboot2backend.domain.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}