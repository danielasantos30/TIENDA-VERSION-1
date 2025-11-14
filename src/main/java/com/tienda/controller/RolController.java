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
import com.tienda.DTO.RolDTO;
import com.tienda.service.IRolService;

@RestController
@RequestMapping("/roles")
public class RolController {
	
	
 @Autowired	
	private IRolService rolService;

 
 @GetMapping
	public List <RolDTO> obtenerTodosLosRoles(){
		return rolService.obtenerTodosLosRoles();
	}
	
	//eliminar categoria
	@DeleteMapping("/{id}")
	public String deleteRol(@PathVariable("id") int idRol) {
	    rolService.eliminarRol(idRol); // ← Una llamada, toda la lógica en Service
	    return "Rol eliminado correctamente";
	}
 
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerRolPorId(@PathVariable int id) {
		RolDTO rol =rolService.obtenerRolPorId(id);
		
		if(rol != null) {
			//si no es nulo, regresa HTTP 200 + JSON
			return ResponseEntity.ok(rol);
			}else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("No se encontró el rol con ID" + id);
	}
	}
	
	@PostMapping
	public ResponseEntity<Object> crearRol (@RequestBody RolDTO rolDTO){		
	try {
		RolDTO rolCreado = rolService.crearRol(rolDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(rolCreado);
						} catch (ServiceException e) {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());						}
	}
	
	@PutMapping("/{id}")
	public String actualizarRol(@PathVariable int id, @RequestBody RolDTO rolDTO) {
		rolDTO.setIdRol(id);
		RolDTO rolActualizado = rolService.actualizarRol(rolDTO);
		if(rolActualizado != null) {
			return "Rol actualizado :  " + rolActualizado;
		}else { return "no se pudo actualizar el rol con ID :  " + id;}
	
	}
}
