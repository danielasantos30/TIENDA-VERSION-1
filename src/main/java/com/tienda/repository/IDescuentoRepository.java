package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.entity.DescuentoEntity;

public interface IDescuentoRepository extends JpaRepository<DescuentoEntity, Integer>{

}
