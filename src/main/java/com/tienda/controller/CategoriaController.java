package com.tienda.controller;
//pagina controller ejemplo de cambio
//otro ejemplo

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.DTO.CategoriaDTO;

import com.tienda.service.ICategoriaService;

@RestController
@RequestMapping("/categorias")
 
public class CategoriaController {
	
	//private final ICategoriaRepository categoriaRepository;
	
	@Autowired
	private ICategoriaService categoriaService;
	
	//CategoriaController(ICategoriaRepository categoriaRepository){
	//	this.categoriaRepository = categoriaRepository;
		
	//}
	//obtener todas las categorias
	@GetMapping
	public List <CategoriaDTO> obtenerTodasLasCategorias(){
		return categoriaService.obtenerTodasLasCategorias();
	}
	
	//eliminar categoria
	@DeleteMapping("/{id}")
	public String deleteCategoria(@PathVariable("id") int idcategoria) {
	    categoriaService.eliminarCategoriaSiExiste(idcategoria); // ← Una llamada, toda la lógica en Service
	    return "Categoria eliminado correctamente";
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerCategoriaPorId(@PathVariable int id) {
		CategoriaDTO categoria =categoriaService.obtenerCategoriaPorId(id);
		
		if(categoria != null) {
			//si no es nulo, regresa HTTP 200 + JSON
			return ResponseEntity.ok(categoria);
			}else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("No se encontró la categoría con ID" + id);
	}
	}

	//agregar categoria
	
	@PostMapping
	public ResponseEntity<Object> crearCategoria (@RequestBody CategoriaDTO categoriaDTO){		
	try {
		CategoriaDTO categoriaCreada = categoriaService.crearCategoria(categoriaDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCreada);
						} catch (ServiceException e) {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());						}
	}
	

	//actualizar categoria
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarCategoria(@PathVariable int id, @RequestBody CategoriaDTO categoriaDTO) {
		try {
			CategoriaDTO categoriaActualizada = categoriaService.actualizarCategoria(id, categoriaDTO);
		    return ResponseEntity.ok(categoriaActualizada);
		}catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
					}
	   
	}
	

}
