package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.entity.ProductoDescuentoEntity;

public interface IProductoDescuentoRepository  extends JpaRepository<ProductoDescuentoEntity, Integer>  {

}
