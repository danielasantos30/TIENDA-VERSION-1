package com.tienda.DTO;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class InventarioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2326499033092218103L;
	
private int idinventario;
private ProductoDTO idproducto;
private SucursalDTO idsucursal;
private int cantidad;
private int cantMinima;


public InventarioDTO() {
	super();
}


public InventarioDTO(int idinventario, ProductoDTO idproducto, SucursalDTO idsucursal, int cantidad, int cantMinima) {
	super();
	this.idinventario = idinventario;
	this.idproducto = idproducto;
	this.idsucursal = idsucursal;
	this.cantidad = cantidad;
	this.cantMinima = cantMinima;
}



}
