package com.tienda.service;

import java.util.List;

import com.tienda.DTO.ProductoDTO;

public interface IProductoService {
	
	//métodos
	
	ProductoDTO obtenerProductoPorId(int id);
	
	List<ProductoDTO> obtenerTodosLosProductos();
	
	void eliminarProducto (int id);
	
	ProductoDTO actualizarProducto(int id, ProductoDTO productoDTO);
	
	ProductoDTO crearProducto(ProductoDTO productoDTO);
	
	
	
}
