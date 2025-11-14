package com.tienda.repository;
import org.springframework.data.jpa.repository.JpaRepository;


import com.tienda.entity.CategoriaEntity;

public interface ICategoriaRepository extends JpaRepository<CategoriaEntity, Integer>{

}
