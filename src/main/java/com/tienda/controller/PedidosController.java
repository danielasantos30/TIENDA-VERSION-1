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
import com.tienda.DTO.PedidosDTO;
import com.tienda.service.IPedidosService;


@RestController
@RequestMapping("/pedidos")
public class PedidosController {
	
//	private final PedidosService pedidosService;
//	
//	public PedidosController(PedidosService pedidosService) {
//		this.pedidosService= pedidosService;
//	}
//	
	@Autowired
	private IPedidosService iPedidosService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPedidosPorId(@PathVariable int id){
		PedidosDTO pedido =iPedidosService.obtenerPedidoPorId(id);
	if (pedido !=null) {
		return ResponseEntity.ok(pedido);
	}else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("No se encontró el pedido con ID:  " + id);
	}	
	}
	
	@GetMapping
	public List<PedidosDTO> obtenerTodoslosPedidos(){
		return iPedidosService.obtenerTodosLosPedidos();
	}
	
	@DeleteMapping("/{id}")
	public String deletePedido(@PathVariable int id) {
		iPedidosService.eliminarPedido(id);
		return "Pedido eliminado correctamente";
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarPedido(@PathVariable int id, @RequestBody PedidosDTO pedidoDTO){
		try {
			PedidosDTO pedidoActualizado = iPedidosService.actualizarPedido(id, pedidoDTO);
			return ResponseEntity.ok(pedidoActualizado);
					}catch(RuntimeException e) {
						return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
					}
	}
	
	@PostMapping
	public ResponseEntity<Object> crearPedido(@RequestBody PedidosDTO  pedidoDTO){
		try {
			PedidosDTO pedidoCreado = iPedidosService.crearPedido(pedidoDTO);
			return ResponseEntity.status( HttpStatus.CREATED).body(pedidoCreado);
			}catch (ServiceException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
	}
	
}
