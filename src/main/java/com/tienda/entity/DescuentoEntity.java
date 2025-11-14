package com.tienda.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "descuentos")
public class DescuentoEntity implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 4120637932595800145L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_descuento")
    private int idDescuento;
    
    @Column(name = "nombre_descuento", nullable = false, length = 150)
    private String nombreDescuento;
    
    @Column(name = "porcentaje_desc", nullable = false)
    private Double porcentajeDesc;
    
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;
    
    @Column(name = "fecha_fin", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaFin;
   
    @Column(name = "fecha_creacion")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCreacion;
    
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fecha_eliminacion")
    private LocalDateTime fechaEliminacion;
    
    @Column(name = "usuario_creacion", length = 100)
    private String usuarioCreacion;
    
    @Column(name = "usuario_eliminacion", length = 100)
    private String usuarioEliminacion;
    
    @Column(name = "estatus", length = 20)
    private String estatus;
    
    
    @OneToMany(mappedBy = "idDescuento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductoDescuentoEntity> productoDesc = new ArrayList<>();

	public int getIdDescuento() {
		return idDescuento;
	}

	public void setIdDescuento(int idDescuento) {
		this.idDescuento = idDescuento;
	}

	public String getNombreDescuento() {
		return nombreDescuento;
	}

	public void setNombreDescuento(String nombreDescuento) {
		this.nombreDescuento = nombreDescuento;
	}

	public Double getPorcentajeDesc() {
		return porcentajeDesc;
	}

	public void setPorcentajeDesc(Double porcentajeDesc) {
		this.porcentajeDesc = porcentajeDesc;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}



	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaEliminacion() {
		return fechaEliminacion;
	}

	public void setFechaEliminacion(LocalDateTime fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioEliminacion() {
		return usuarioEliminacion;
	}

	public void setUsuarioEliminacion(String usuarioEliminacion) {
		this.usuarioEliminacion = usuarioEliminacion;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
    
    
}