package com.tienda.service;

import java.util.List;

import com.tienda.DTO.ProductoDescuentoDTO;

public interface IProductoDescuentoService {
	
	ProductoDescuentoDTO obtenerProductoDescuentoPorId(int id);
	
	List<ProductoDescuentoDTO> obtenerTodosLosProductoDescuentos();
		
	void eliminarProductoDescuento(int id);
		
	ProductoDescuentoDTO actualizarProductoDescuento(int id, ProductoDescuentoDTO productoDescuentoDTO);
		
	ProductoDescuentoDTO crearProductoDescuento(ProductoDescuentoDTO productoDescuentoDTO);

}
