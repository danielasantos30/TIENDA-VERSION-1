package com.tienda.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "traspasos")
public class TraspasoEntity implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 2162015457750497941L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_traspaso")
    private int idTraspaso;
    
	
	@ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", nullable = true)
    private ProductoEntity idProducto;
    
	@ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = true)
    private UsuarioEntity idUsuario;
    
	@ManyToOne
    @JoinColumn(name = "sucursal_origen", referencedColumnName = "id_sucursal", nullable = true)
    private SucursalEntity sucursalOrigen;
    
	@ManyToOne
    @JoinColumn(name = "sucursal_destino", referencedColumnName = "id_sucursal", nullable = true)
    private SucursalEntity sucursalDestino;
    
    @Column(name = "cantidad")
    private int cantidad;
    
    @Column(name = "fecha_traspaso")
    private LocalDateTime fechaTraspaso;
    
    @Column(name = "estado")
    private String estado;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    @Column(name = "fecha_eliminacion")
    private LocalDateTime fechaEliminacion;
    
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    
    @Column(name = "usuario_eliminacion")
    private String usuarioEliminacion;
    
    @Column(name = "fecha_confirmacion")
    private LocalDateTime fechaConfirmacion;

	public int getIdTraspaso() {
		return idTraspaso;
	}

	public void setIdTraspaso(int idTraspaso) {
		this.idTraspaso = idTraspaso;
	}


	public ProductoEntity getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(ProductoEntity idProducto) {
		this.idProducto = idProducto;
	}

	public UsuarioEntity getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(UsuarioEntity idUsuario) {
		this.idUsuario = idUsuario;
	}

	public SucursalEntity getSucursalOrigen() {
		return sucursalOrigen;
	}

	public void setSucursalOrigen(SucursalEntity sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}

	public SucursalEntity getSucursalDestino() {
		return sucursalDestino;
	}

	public void setSucursalDestino(SucursalEntity sucursalDestino) {
		this.sucursalDestino = sucursalDestino;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDateTime getFechaTraspaso() {
		return fechaTraspaso;
	}

	public void setFechaTraspaso(LocalDateTime fechaTraspaso) {
		this.fechaTraspaso = fechaTraspaso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public LocalDateTime getFechaConfirmacion() {
		return fechaConfirmacion;
	}

	public void setFechaConfirmacion(LocalDateTime fechaConfirmacion) {
		this.fechaConfirmacion = fechaConfirmacion;
	}
    
    
}