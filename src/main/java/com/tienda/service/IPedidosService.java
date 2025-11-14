package com.tienda.service;

import java.util.List;

import com.tienda.DTO.PedidosDTO;

public interface IPedidosService {
	
	PedidosDTO obtenerPedidoPorId(int id);
	
	List<PedidosDTO> obtenerTodosLosPedidos();
		
	void eliminarPedido(int id);
		
	PedidosDTO actualizarPedido(int id, PedidosDTO pedidoDTO);
		
	PedidosDTO crearPedido(PedidosDTO pedidoDTO);

}
