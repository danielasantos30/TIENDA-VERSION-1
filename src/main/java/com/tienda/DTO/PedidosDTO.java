package com.tienda.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PedidosDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1793613309371109349L;
	

	private int idPedido;
	private ClienteDTO idCliente;
	private UsuarioDTO idUsuario;
	private SucursalDTO idSucursal;
	private java.time.LocalDateTime fechaPedido;
	private java.time.LocalDateTime fechaCreacion;
	private String estatus;
	
	public PedidosDTO() {
		super();
	}

	public PedidosDTO(int idPedido, ClienteDTO idCliente, UsuarioDTO idUsuario, SucursalDTO idSucursal,
			LocalDateTime fechaPedido, LocalDateTime fechaCreacion, String estatus) {
		super();
		this.idPedido = idPedido;
		this.idCliente = idCliente;
		this.idUsuario = idUsuario;
		this.idSucursal = idSucursal;
		this.fechaPedido = fechaPedido;
		this.fechaCreacion = fechaCreacion;
		this.estatus = estatus;
	}
	
	
	
}
