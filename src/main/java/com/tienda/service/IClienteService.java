package com.tienda.service;

import java.util.List;

import com.tienda.DTO.ClienteDTO;

public interface IClienteService {
	
	ClienteDTO obtenerClientePorId (int id);
	List<ClienteDTO> obtenerTodosLosClientes();
	ClienteDTO eliminarCliente (int id);
	ClienteDTO actualizarCliente(ClienteDTO clienteDTO);
	ClienteDTO crearCliente(ClienteDTO clienteDTO);
	

}
