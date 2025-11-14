package com.tienda.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventario")
public class InventarioEntity implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -8480456162762263388L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario")
    private int idinventario;
    
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto",referencedColumnName = "id_producto")
    private ProductoEntity idproducto;
    
	@ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "id_sucursal",referencedColumnName = "id_sucursal")
    private SucursalEntity idsucursal;
    
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    
    @Column(name = "cant_minima")
    private int cantMinima;
    
    @Column(name = "fecha_ingreso")
    private LocalDateTime fechaIngreso;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    @Column(name = "fecha_eliminacion")
    private LocalDateTime fechaEliminacion;
    
    @Column(name = "usuario_creacion", length = 100)
    private String usuarioCreacion;
    
    @Column(name = "usuario_eliminacion", length = 100)
    private String usuarioEliminacion;

	public int getIdInventario() {
		return idinventario;
	}

	public void setIdInventario(int idInventario) {
		this.idinventario = idInventario;
	}

	public ProductoEntity getIdProducto() {
		return idproducto;
	}

	public void setIdProducto(ProductoEntity idProducto) {
		this.idproducto = idProducto;
	}


	public SucursalEntity getIdSucursal() {
		return idsucursal;
	}

	public void setIdSucursal(SucursalEntity idSucursal) {
		this.idsucursal = idSucursal;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCantMinima() {
		return cantMinima;
	}

	public void setCantMinima(int cantMinima) {
		this.cantMinima = cantMinima;
	}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
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