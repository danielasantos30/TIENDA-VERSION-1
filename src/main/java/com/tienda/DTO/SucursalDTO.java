package com.tienda.DTO;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SucursalDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -705749250636882393L;
	
	private int idSucursal;
	private String nombre;
	private String direccion;
	private String telefono;
	private String ubicacion;
	private String rfc;
	private String imagenUrl;
	private Double latitud;
	private Double longitud;
	public SucursalDTO() {
		super();
	}
	public SucursalDTO(int idSucursal, String nombre, String direccion, String telefono, String ubicacion, String rfc,
			String imagenUrl, Double latitud, Double longitud) {
		super();
		this.idSucursal = idSucursal;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.ubicacion = ubicacion;
		this.rfc = rfc;
		this.imagenUrl = imagenUrl;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	

}
