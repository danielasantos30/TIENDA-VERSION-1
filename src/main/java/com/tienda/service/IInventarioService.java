package com.tienda.service;

import java.util.List;

import com.tienda.DTO.InventarioDTO;

public interface IInventarioService {
	
	InventarioDTO obtenerInventarioPorId(int id);
	
	List<InventarioDTO> obtenerTodosLosInventarios();
		
	void eliminarInventario(int id);
		
	InventarioDTO actualizarInventario(int id, InventarioDTO inventarioDTO);
		
	InventarioDTO crearInventario(InventarioDTO inventarioDTO);

}
