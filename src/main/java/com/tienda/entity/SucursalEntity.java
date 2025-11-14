package com.tienda.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sucursales")
public class SucursalEntity implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 5058024911616144145L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private int idSucursal;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "telefono")
    private String telefono;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    @Column(name = "fecha_eliminacion")
    private LocalDateTime fechaEliminacion;
    
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    
    @Column(name = "usuario_eliminacion")
    private String usuarioEliminacion;
    
    @Column(name = "estatus")
    private String estatus;
    
    @Column(name = "ubicacion")
    private String ubicacion;
    
    @Column(name = "rfc")
    private String rfc;
    
    @Column(name = "imagen_url")
    private String imagenUrl;
    
    @Column(name = "latitud")
    private Double latitud;
    
    @Column(name = "longitud")
    private Double longitud;
    
    @OneToMany(mappedBy = "idsucursal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InventarioEntity> inventario = new ArrayList<>();
 
    @OneToMany(mappedBy = "idsucursal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PedidosEntity> pedidos = new ArrayList<>();
 
//    @OneToMany(mappedBy = "sucursalOrigen")
//    private List<SucursalEntity> sucursalOrigen = new ArrayList<>();
//    
//    @OneToMany(mappedBy = "sucursalDestino")
//    private List<SucursalEntity> sucursalDestino = new ArrayList<>();
    
	public int getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
    
   
}