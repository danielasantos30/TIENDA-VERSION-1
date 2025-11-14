package com.tienda.DTO;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ClienteDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4605252395325433578L;
	
	
	private int idcliente;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String direccion;
	
	public ClienteDTO() {
		super();
	}

	public ClienteDTO(int idcliente, String nombre, String apellido, String email, String telefono, String direccion) {
		super();
		this.idcliente = idcliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.direccion =direccion;
	}
	
	

}
