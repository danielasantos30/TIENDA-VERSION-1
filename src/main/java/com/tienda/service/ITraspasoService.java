package com.tienda.service;

import java.util.List;

import com.tienda.DTO.TraspasoDTO;

public interface ITraspasoService {
	
	TraspasoDTO obtenerTraspasoPorId(int id);
	List<TraspasoDTO> obtenerTodosLosTraspasos();
	void eliminarTraspaso(int id);
	TraspasoDTO actualizarTraspaso(int id, TraspasoDTO traspasoDTO);
	TraspasoDTO crearTraspaso(TraspasoDTO traspasoDTO);
	
	

}
