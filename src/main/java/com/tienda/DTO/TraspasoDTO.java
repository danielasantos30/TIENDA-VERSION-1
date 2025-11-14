package com.tienda.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TraspasoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8456399797031714733L;

	private int idTraspaso;
	private ProductoDTO idProducto;
	private UsuarioDTO idUsuario;
	private SucursalDTO sucursalOrigen;
	private SucursalDTO sucursalDestino;
	private int cantidad;
	private java.time.LocalDateTime fechaTraspaso;
	private String estado;
	public TraspasoDTO() {
		super();
	}
	public TraspasoDTO(int idTraspaso, ProductoDTO idProducto, UsuarioDTO idUsuario, SucursalDTO sucursalOrigen,
			SucursalDTO sucursalDestino, int cantidad, LocalDateTime fechaTraspaso, String estado) {
		super();
		this.idTraspaso = idTraspaso;
		this.idProducto = idProducto;
		this.idUsuario = idUsuario;
		this.sucursalOrigen = sucursalOrigen;
		this.sucursalDestino = sucursalDestino;
		this.cantidad = cantidad;
		this.fechaTraspaso = fechaTraspaso;
		this.estado = estado;
	}
	
	
	
}
