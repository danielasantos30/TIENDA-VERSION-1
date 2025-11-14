package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.entity.InventarioEntity;


public interface IInventarioRepository  extends JpaRepository<InventarioEntity, Integer>  {

}
