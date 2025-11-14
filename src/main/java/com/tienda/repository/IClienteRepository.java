package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.entity.ClienteEntity;



public interface IClienteRepository extends JpaRepository<ClienteEntity, Integer>{


}
