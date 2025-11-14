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

import com.tienda.DTO.UsuarioDTO;
import com.tienda.service.IUsuarioService;

@RestController
@RequestMapping("/usuarios") // Cambiado de /productos a /usuarios
public class UsuarioController { // Cambiado de ProductoController a UserController
	@Autowired
	private IUsuarioService usuarioService; // Cambiado de IProductoService a IUsuarioService
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable int id){ // Cambiado de Producto a Usuario
		UsuarioDTO usuario = usuarioService.obtenerUsuarioPorId(id); // Cambiado de Producto a Usuario
	if (usuario !=null) {
		return ResponseEntity.ok(usuario);
	}else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("No se encontró el usuario con ID " + id); // Mensaje de error cambiado a Usuario
	}
	
	}

@GetMapping
public List<UsuarioDTO> obtenerTodosLosUsuarios(){ // Cambiado de Productos a Usuarios
return usuarioService.obtenerTodosLosUsuarios(); // Cambiado de Productos a Usuarios
	}

@DeleteMapping("/{id}")
public String deleteUsuario(@PathVariable("id") int idUsuario) { // Cambiado de Producto a Usuario
	usuarioService.eliminarUsuario(idUsuario); // Cambiado de Producto a Usuario
	return "Usuario eliminado correctamente"; // Mensaje cambiado a Usuario
	}

@PutMapping("/{id}")
public ResponseEntity<?> actualizarUsuario(@PathVariable int id, @RequestBody UsuarioDTO usuarioDTO){ // Cambiado de Producto a Usuario
	try {
		UsuarioDTO usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioDTO); // Cambiado de Producto a Usuario
		return ResponseEntity.ok(usuarioActualizado);
				}catch(RuntimeException e) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
				}
}

@PostMapping
public ResponseEntity<Object> crearUsuario(@RequestBody UsuarioDTO usuarioDTO){ // Cambiado de Producto a Usuario
	try {
		UsuarioDTO usuarioCreado = usuarioService.crearUsuario(usuarioDTO); // Cambiado de Producto a Usuario
		return ResponseEntity.status( HttpStatus.CREATED).body(usuarioCreado);
		}catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
}

}