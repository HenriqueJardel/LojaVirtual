package com.jt.springboot2backend.repositories;

import com.jt.springboot2backend.domain.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
