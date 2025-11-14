package com.tienda.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class DescuentoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4791595398911590111L;
	
	private int idDescuento;
	private String nombreDescuento;
	private Double porcentajeDesc;
	private java.time.LocalDateTime fechaInicio;
	private java.time.LocalDateTime fechaFin;
	public DescuentoDTO() {
		super();
	}
	public DescuentoDTO(int idDescuento, String nombreDescuento, Double porcentajeDesc, LocalDateTime fechaInicio,
			LocalDateTime fechaFin) {
		super();
		this.idDescuento = idDescuento;
		this.nombreDescuento = nombreDescuento;
		this.porcentajeDesc = porcentajeDesc;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	
	
}
