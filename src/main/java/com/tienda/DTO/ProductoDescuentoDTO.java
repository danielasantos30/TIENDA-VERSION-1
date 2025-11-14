package com.tienda.DTO;

import java.io.Serializable;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductoDescuentoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 936615486880790655L;
	
	private int idProductoDescuento;
	private DescuentoDTO idDescuento;
	private ProductoDTO idProducto;
	
	
	
	public ProductoDescuentoDTO() {
		super();
	}
	
	public ProductoDescuentoDTO(int idProductoDescuento, DescuentoDTO idDescuento, ProductoDTO idProducto) {
		super();
		this.idProductoDescuento = idProductoDescuento;
		this.idDescuento = idDescuento;
		this.idProducto = idProducto;
	}
	
	
	
	

}
