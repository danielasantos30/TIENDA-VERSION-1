package com.tienda.controller;

import java.util.List;
import com.tienda.service.ProductoDescuentoService;

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

import com.tienda.DTO.ProductoDescuentoDTO;
import com.tienda.service.IProductoDescuentoService;

@RestController
@RequestMapping("/descuentoproducto")
public class ProductoDescuentoController {

    private final ProductoDescuentoService productoDescuentoService;
	
	@Autowired
	private IProductoDescuentoService iProductoDescuentoService;


    ProductoDescuentoController(ProductoDescuentoService productoDescuentoService) {
        this.productoDescuentoService = productoDescuentoService;
    }

	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerProductoDescuentoPorId(@PathVariable int id){
		ProductoDescuentoDTO descuento =iProductoDescuentoService.obtenerProductoDescuentoPorId(id);
	if (descuento !=null) {
		return ResponseEntity.ok(descuento);
	}else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("No se encontró el descuento del producto con ID:  " + id);
	}	
	}
	
	@GetMapping
	public List<ProductoDescuentoDTO> obtenerTodoslosProductoDescuentos(){
		return productoDescuentoService.obtenerTodosLosProductoDescuentos();
	}
	
	@DeleteMapping("/{id}")
	public String deleteProductoDescuento(@PathVariable int id) {
		productoDescuentoService.eliminarProductoDescuento(id);
		return "Descuento de Producto eliminado correctamente";
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarProductoDescuento(@PathVariable int id, @RequestBody ProductoDescuentoDTO productoDescuentoDTO){
		try {
			ProductoDescuentoDTO descuentoActualizado = productoDescuentoService.actualizarProductoDescuento(id, productoDescuentoDTO);
			return ResponseEntity.ok(descuentoActualizado);
					}catch(RuntimeException e) {
						return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
					}
	}
	
	@PostMapping
	public ResponseEntity<Object> crearProductoDescuento(@RequestBody ProductoDescuentoDTO  productoDescuentoDTO){
		try {
			ProductoDescuentoDTO descuentoCreado = productoDescuentoService.crearProductoDescuento(productoDescuentoDTO);
			return ResponseEntity.status( HttpStatus.CREATED).body(descuentoCreado);
			}catch (ServiceException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
	}
	
}
