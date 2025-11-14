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

import com.tienda.DTO.InventarioDTO;

import com.tienda.service.IInventarioService;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

	@Autowired
	private IInventarioService inventarioService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerInventarioPorId(@PathVariable int id){
		InventarioDTO inventario =inventarioService.obtenerInventarioPorId(id);
	if (inventario !=null) {
		return ResponseEntity.ok(inventario);
	}else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("No se encontró el inventario con ID:  " + id);
	}	
	}
	
	@GetMapping
	public List<InventarioDTO> obtenerTodosLosInventarios(){
	return inventarioService.obtenerTodosLosInventarios();
		}
	
	@DeleteMapping("/{id}")
	public String deleteInventario(@PathVariable int id) {
		inventarioService.eliminarInventario(id);
		return "Inventario eliminado correctamente";
		}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarInventario(@PathVariable int id, @RequestBody InventarioDTO inventarioDTO){
		try {
			InventarioDTO inventarioActualizado = inventarioService.actualizarInventario(id, inventarioDTO);
			return ResponseEntity.ok(inventarioActualizado);
					}catch(RuntimeException e) {
						return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
					}
	}
	@PostMapping
	public ResponseEntity<Object> crearInventario(@RequestBody InventarioDTO inventarioDTO){
		try {
			InventarioDTO inventarioCreado = inventarioService.crearInventario(inventarioDTO);
			return ResponseEntity.status( HttpStatus.CREATED).body(inventarioCreado);
			}catch (ServiceException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
	}
}
