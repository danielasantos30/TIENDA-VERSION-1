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

import com.tienda.DTO.ProductoDTO;
import com.tienda.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	@Autowired
	private IProductoService productoService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerProductoPorId(@PathVariable int id){
		ProductoDTO producto =productoService.obtenerProductoPorId(id);
	if (producto !=null) {
		return ResponseEntity.ok(producto);
	}else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("No se encontró el producto con ID" + id);
	}
	
	}

@GetMapping
public List<ProductoDTO> obtenerTodosLosProductos(){
return productoService.obtenerTodosLosProductos();
	}

@DeleteMapping("/{id}")
public String deleteProducto(@PathVariable("id") int idproducto) {
	productoService.eliminarProducto(idproducto);
	return "Producto eliminado correctamente";
	}

@PutMapping("/{id}")
public ResponseEntity<?> actualizarProducto(@PathVariable int id, @RequestBody ProductoDTO productoDTO){
	try {
		ProductoDTO productoActualizado = productoService.actualizarProducto(id, productoDTO);
		return ResponseEntity.ok(productoActualizado);
				}catch(RuntimeException e) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
				}
}

@PostMapping
public ResponseEntity<Object> crearProducto(@RequestBody ProductoDTO productoDTO){
	try {
		ProductoDTO productoCreado = productoService.crearProducto(productoDTO);
		return ResponseEntity.status( HttpStatus.CREATED).body(productoCreado);
		}catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
}

}