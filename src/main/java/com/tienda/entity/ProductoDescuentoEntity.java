package com.tienda.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "producto_descuento")
public class ProductoDescuentoEntity implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 7277212938350359268L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto_descuento")
    private int idProductoDescuento;  
	
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name = "id_descuento", referencedColumnName = "id_descuento")
    private DescuentoEntity idDescuento;
    
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private ProductoEntity idProducto;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    @Column(name = "fecha_eliminacion")
    private LocalDateTime fechaEliminacion;
    
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    
    @Column(name = "usuario_eliminacion")
    private String usuarioEliminacion;

	public int getIdProductoDescuento() {
		return idProductoDescuento;
	}

	public void setIdProductoDescuento(int idProductoDescuento) {
		this.idProductoDescuento = idProductoDescuento;
	}

		public DescuentoEntity getIdDescuento() {
		return idDescuento;
	}

	public void setIdDescuento(DescuentoEntity idDescuento) {
		this.idDescuento = idDescuento;
	}

	public ProductoEntity getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(ProductoEntity idProducto) {
		this.idProducto = idProducto;
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
    
    
   
    
    
}