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
import com.tienda.DTO.DescuentoDTO;
import com.tienda.service.IDescuentoService;

@RestController
@RequestMapping("/descuentos")
public class DescuentoController {
	
	
 @Autowired	
	private IDescuentoService descuentoService;

 
 @GetMapping
	public List <DescuentoDTO> obtenerTodosLosDescuentos(){
		return descuentoService.obtenerTodosLosDescuentos();
	}
	
	//eliminar descuento
	@DeleteMapping("/{id}")
	public String deleteDescuento(@PathVariable("id") int idDescuento) {
	    descuentoService.eliminarDescuento(idDescuento); // ← Una llamada, toda la lógica en Service
	    return "Descuento eliminado correctamente";
	}
 
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerDescuentoPorId(@PathVariable int id) {
		DescuentoDTO descuento = descuentoService.obtenerDescuentoPorId(id);
		
		if(descuento != null) {
			//si no es nulo, regresa HTTP 200 + JSON
			return ResponseEntity.ok(descuento);
			}else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("No se encontró el descuento con ID" + id);
	}
	}
	
	@PostMapping
	public ResponseEntity<Object> crearDescuento (@RequestBody DescuentoDTO descuentoDTO){		
	try {
		DescuentoDTO descuentoCreado = descuentoService.crearDescuento(descuentoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(descuentoCreado);
						} catch (ServiceException e) {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());						}
	}
	
	@PutMapping("/{id}")
	public String actualizarDescuento(@PathVariable int id, @RequestBody DescuentoDTO descuentoDTO) {
		descuentoDTO.setIdDescuento(id);
		DescuentoDTO descuentoActualizado = descuentoService.actualizarDescuento(descuentoDTO);
		if(descuentoActualizado != null) {
			return "Descuento actualizado :  " + descuentoActualizado;
		}else { return "no se pudo actualizar el descuento con ID :  " + id;}
	
	}
}