package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.entity.UsuarioEntity;


public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{

}
