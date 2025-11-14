package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.entity.RolEntity;



public interface IRolRepository extends JpaRepository<RolEntity, Integer>{

}
