package com.tienda.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -4927834440074190428L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idusuario;
    
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    private RolEntity idRol;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "apellido")
    private String apellido;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "telefono")
    private String telefono;
    
    @Column(name = "direccion")
    private String direccion;
    
    @CreationTimestamp
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
    
    @Column(name = "curp")
    private String curp;
    
    @Column(name = "tipo_sangre")
    private String tipoSangre;
    
    @Column(name = "imagen_url")
    private String imagenUrl;
    
    
    @OneToMany(mappedBy = "idusuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PedidosEntity> pedidos = new ArrayList<>();
 
    
    @OneToMany(mappedBy = "idUsuario", cascade = CascadeType.ALL)
    private List<TraspasoEntity> traspaso = new ArrayList<>();
    
    
    //relaciones INVERSAS con Categorías
   // @OneToMany(mappedBy = "usuarioCreacion")
    //private List<CategoriaEntity> categoriasCreadas =new ArrayList<>();
    
  //  @OneToMany(mappedBy = "usuarioEliminacion")
  //  private List<CategoriaEntity> categoriasEliminadas = new ArrayList<>();
    
 // RELACIONES INVERSA con Usuarios (auto-referencia)
    //@OneToMany(mappedBy = "usuarioCreacion")
   // private List<UsuarioEntity> usuariosCreados = new ArrayList<>();
    
   // @OneToMany(mappedBy = "usuarioEliminacion")
  //  private List<UsuarioEntity> usuariosEliminados = new ArrayList<>();

    
    
//getters y setters
	public int getIdUsuario() {
		return idusuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idusuario = idUsuario;
	}

	public RolEntity getIdRol() {
		return idRol;
	}

	public void setIdRol(RolEntity idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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


	public String getUsuarioEliminacion() {
		return usuarioEliminacion;
	}

	public void setUsuarioEliminacion(String usuarioEliminacion) {
		this.usuarioEliminacion = usuarioEliminacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

//	public List<CategoriaEntity> getCategoriasCreadas() {
//		return categoriasCreadas;
//	}
//
//	public void setCategoriasCreadas(List<CategoriaEntity> categoriasCreadas) {
//		this.categoriasCreadas = categoriasCreadas;
//	}
//
//	public List<CategoriaEntity> getCategoriasEliminadas() {
//		return categoriasEliminadas;
//	}
//
//	public void setCategoriasEliminadas(List<CategoriaEntity> categoriasEliminadas) {
//		this.categoriasEliminadas = categoriasEliminadas;
//	}
//
//	public List<UsuarioEntity> getUsuariosCreados() {
//		return usuariosCreados;
//	}
//
//	public void setUsuariosCreados(List<UsuarioEntity> usuariosCreados) {
//		this.usuariosCreados = usuariosCreados;
//	}
//
//	public List<UsuarioEntity> getUsuariosEliminados() {
//		return usuariosEliminados;
//	}
//
//	public void setUsuariosEliminados(List<UsuarioEntity> usuariosEliminados) {
//		this.usuariosEliminados = usuariosEliminados;
//	}
    
    
}