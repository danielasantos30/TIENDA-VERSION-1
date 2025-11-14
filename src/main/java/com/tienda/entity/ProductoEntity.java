package com.tienda.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="productos")
public class ProductoEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2707206094930504341L;
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="id_producto")
	private int idproducto;
	
	//foreing key.relacion con categoria
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_categoria", referencedColumnName="id_categoria")
	private CategoriaEntity idcategoria;
	
	@Column(name = "nombre", length = 300)	
	private String nombre;
	
	@Column(name = "precio", length = 300)	
	private Double precio;
	
	@Column(name = "marca", length = 300)	
	private String marca;
	
	 @Column(name = "descripcion", columnDefinition = "TEXT")
	    private String descripcion;
	    
	    @Column(name = "imagen_url", length = 500)
	    private String imagenUrl;
	    
	    @Column(name = "fecha_creacion")
	    private LocalDateTime fechaCreacion;
	    
	    @Column(name = "fecha_eliminacion")
	    private LocalDateTime fechaEliminacion;
	    
	    @Column(name = "usuario_creacion", length = 100)
	    private String usuarioCreacion;
	    
	    @Column(name = "usuario_eliminacion", length = 100)
	    private String usuarioEliminacion;
	    
	    @Column(name = "estatus", length = 20)
	    private String estatus;
	    
	    
	    @OneToMany(mappedBy = "idproducto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<InventarioEntity> inventario = new ArrayList<>();
	 
	    @OneToMany(mappedBy = "idProducto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<ProductoDescuentoEntity> productoDescuento = new ArrayList<>();
	    
	    
	    @OneToMany(mappedBy = "idProducto", cascade = CascadeType.ALL)
	    private List<TraspasoEntity> traspaso = new ArrayList<>();
	    
	    //getters y setters

		public int getIdproducto() {
			return idproducto;
		}

		public void setIdproducto(int idpproducto) {
			this.idproducto = idpproducto;
		}

		
		public CategoriaEntity getIdcategoria() {
			return idcategoria;
		}

		public void setIdcategoria(CategoriaEntity idcategoria) {
			this.idcategoria = idcategoria;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public Double getPrecio() {
			return precio;
		}

		public void setPrecio(Double precio) {
			this.precio = precio;
		}

		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public String getImagenUrl() {
			return imagenUrl;
		}

		public void setImagenUrl(String imagenUrl) {
			this.imagenUrl = imagenUrl;
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
