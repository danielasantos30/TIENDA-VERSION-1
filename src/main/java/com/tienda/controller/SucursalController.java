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
import com.tienda.DTO.SucursalDTO;
import com.tienda.service.ISucursalService;

@RestController
@RequestMapping("/sucursales")
public class SucursalController {
	
	
 @Autowired	
	private ISucursalService sucursalService;

 
 @GetMapping
	public List <SucursalDTO> obtenerTodasLasSucursales(){
		return sucursalService.obtenerTodasLasSucursales();
	}
	
	//eliminar sucursal
	@DeleteMapping("/{id}")
	public String deleteSucursal(@PathVariable("id") int idSucursal) {
	    sucursalService.eliminarSucursal(idSucursal); // ← Una llamada, toda la lógica en Service
	    return "Sucursal eliminada correctamente";
	}
 
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerSucursalPorId(@PathVariable int id) {
		SucursalDTO sucursal = sucursalService.obtenerSucursalPorId(id);
		
		if(sucursal != null) {
			//si no es nulo, regresa HTTP 200 + JSON
			return ResponseEntity.ok(sucursal);
			}else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("No se encontró la sucursal con ID" + id);
	}
	}
	
	@PostMapping
	public ResponseEntity<Object> crearSucursal (@RequestBody SucursalDTO sucursalDTO){		
	try {
		SucursalDTO sucursalCreada = sucursalService.crearSucursal(sucursalDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(sucursalCreada);
						} catch (ServiceException e) {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());						}
	}
	
	@PutMapping("/{id}")
	public String actualizarSucursal(@PathVariable int id, @RequestBody SucursalDTO sucursalDTO) {
		sucursalDTO.setIdSucursal(id);
		SucursalDTO sucursalActualizada = sucursalService.actualizarSucursal(sucursalDTO);
		if(sucursalActualizada != null) {
			return "Sucursal actualizada :  " + sucursalActualizada;
		}else { return "no se pudo actualizar la sucursal con ID :  " + id;}
	
	}
}