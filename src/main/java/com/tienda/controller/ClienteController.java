package com.tienda.controller;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.DTO.ClienteDTO;

import com.tienda.service.IClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

   
	@Autowired
	private IClienteService clienteService;

  
	
	@GetMapping
	public List<ClienteDTO> obtenerTodosLosClientes(){
		return clienteService.obtenerTodosLosClientes();		
	}
	
	@DeleteMapping("/{id}")
	public String deleteCliente(@PathVariable("id") int id) {
	    clienteService.eliminarCliente(id); // ← Una llamada, toda la lógica en Service
	    return "Cliente eliminado correctamente";
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerClientePorId(@PathVariable int id) {
		ClienteDTO cliente =clienteService.obtenerClientePorId(id);
		
		if(cliente != null) {
			//si no es nulo, regresa HTTP 200 + JSON
			return ResponseEntity.ok(cliente);
			}else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("No se encontró el cliente con ID" + id);
	}
	}
	@PostMapping
	public ResponseEntity<Object> crearCliente (@RequestBody ClienteDTO clienteDTO){		
	try {
		ClienteDTO clienteCreado = clienteService.crearCliente(clienteDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado);
						} catch (ServiceException e) {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());						}
	}
	@PutMapping("/{id}")
	public String actualizarCliente(@PathVariable int id, @RequestBody ClienteDTO clienteDTO) {
		clienteDTO.setIdcliente(id);
		ClienteDTO clienteActu = clienteService.actualizarCliente(clienteDTO);
		if(clienteActu != null) {
			return "Cliente actualizado :  " + clienteActu;
		}else { return "No se pudo actualizar el cliente con ID :  " + id;}
	
	}
	
	
	

}
