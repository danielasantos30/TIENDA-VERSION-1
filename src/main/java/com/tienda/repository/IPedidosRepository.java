package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.entity.PedidosEntity;

public interface IPedidosRepository  extends JpaRepository<PedidosEntity, Integer>{

}
