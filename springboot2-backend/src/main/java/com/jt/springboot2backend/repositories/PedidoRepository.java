package com.jt.springboot2backend.repositories;

import org.springframework.data.domain.Pageable;

import com.jt.springboot2backend.domain.Cliente;
import com.jt.springboot2backend.domain.Pedido;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    Page<Pedido> findByCliente(Cliente cliente, Pageable pageResquest);
}