package com.tienda.DTO;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class CategoriaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7367172434126750683L;
	
	 private int idcategoria;
	    private String nombre;
	    private String descripcion;
//	    private LocalDateTime fechaCreacion;
//	    private LocalDateTime fechaEliminacion;
//	    private Integer usuarioCreacion;
//	    private Integer usuarioEliminacion;
//	
	    
	  //constructor vacio
	    public CategoriaDTO() {
	    	
	    }


		public CategoriaDTO(int idcategoria, String nombre, String descripcion //LocalDateTime fechaCreacion,
				//LocalDateTime fechaEliminacion, UsuarioDTO usuarioCreacionDTO, UsuarioDTO usuarioEliminacionDTO
				) {
			
			this.idcategoria = idcategoria;
			this.nombre = nombre;
			this.descripcion = descripcion;
//			this.fechaCreacion = fechaCreacion;
//			this.fechaEliminacion = fechaEliminacion;
//			this.usuarioCreacion = usuarioCreacionDTO;
//			this.usuarioEliminacion = usuarioEliminacionDTO;
		}
		//Método que convierte un objeto en una cadena de texto legible
		//El método toString() debe ir en AlumnoDTO porque es donde están definidos los datos que quieres mostrar, los cuales son privados
		//@Override
		//public String toString() {
		//    return "CategoriaDTO{id=" + idCategoria + ", nombre='" + nombre;
		//}

	
}
