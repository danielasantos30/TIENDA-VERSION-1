package com.tienda.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos") // Asumiendo que la tabla se llama "pedidos"
public class PedidosEntity implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -2238307364065834218L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private int idpedido;
    
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private ClienteEntity idcliente;
    
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private UsuarioEntity idusuario;
    
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
    private SucursalEntity idsucursal;
    
    @Column(name = "fecha_pedido")
    private LocalDateTime fechaPedido;
    
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

	public int getIdPedido() {
		return idpedido;
	}

	public void setIdPedido(int idPedido) {
		this.idpedido = idPedido;
	}

	
	public ClienteEntity getIdCliente() {
		return idcliente;
	}

	public void setIdCliente(ClienteEntity idCliente) {
		this.idcliente = idCliente;
	}

	public UsuarioEntity getIdUsuario() {
		return idusuario;
	}

	public void setIdUsuario(UsuarioEntity idUsuario) {
		this.idusuario = idUsuario;
	}

	public SucursalEntity getIdSucursal() {
		return idsucursal;
	}

	public void setIdSucursal(SucursalEntity idSucursal) {
		this.idsucursal = idSucursal;
	}

	public LocalDateTime getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(LocalDateTime fechaPedido) {
		this.fechaPedido = fechaPedido;
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