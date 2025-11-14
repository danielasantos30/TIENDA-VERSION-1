package com.tienda.DTO;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RolDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8756799476660322539L;

	private int idRol;
	private String rol;
	private String descripcion;
	
	
	public RolDTO() {
		super();
	}


	public RolDTO(int idRol, String rol, String descripcion) {
		super();
		this.idRol = idRol;
		this.rol = rol;
		this.descripcion = descripcion;
	}
	
	
	
	
}
