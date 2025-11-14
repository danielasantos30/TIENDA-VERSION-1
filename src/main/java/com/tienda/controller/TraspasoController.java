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
import com.tienda.DTO.TraspasoDTO;
import com.tienda.service.ITraspasoService;

@RestController
@RequestMapping("/traspasos")
public class TraspasoController {	
	@Autowired
	private ITraspasoService iTraspasoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerTraspasoPorId(@PathVariable int id){
		TraspasoDTO traspaso =iTraspasoService.obtenerTraspasoPorId(id);
	if (traspaso !=null) {
		return ResponseEntity.ok(traspaso);
	}else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("No se encontró el traspaso con ID:  " + id);
	}	
	}


	@GetMapping
	public List<TraspasoDTO> obtenerTodoslosTraspasos(){
		return iTraspasoService.obtenerTodosLosTraspasos();
	}
	
	@DeleteMapping("/{id}")
	public String deleteTraspaso(@PathVariable int id) {
		iTraspasoService.eliminarTraspaso(id);
		return "Traspaso eliminado correctamente";
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarTraspaso(@PathVariable int id, @RequestBody TraspasoDTO traspasoDTO){
		try {
			TraspasoDTO traspasoActualizado = iTraspasoService.actualizarTraspaso(id, traspasoDTO);
			return ResponseEntity.ok(traspasoActualizado);
					}catch(RuntimeException e) {
						return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
					}
	}
	
	@PostMapping
	public ResponseEntity<Object> crearTraspaso(@RequestBody TraspasoDTO  traspasoDTO){
		try {
			TraspasoDTO traspasoCreado = iTraspasoService.crearTraspaso(traspasoDTO);
			return ResponseEntity.status( HttpStatus.CREATED).body(traspasoCreado);
			}catch (ServiceException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
	}
	
}
