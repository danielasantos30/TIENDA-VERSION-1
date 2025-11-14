package com.tienda.DTO;

import java.io.Serializable;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioDTO implements Serializable{
	    /**
	 * 
	 */
	
	
	private static final long serialVersionUID = 5230553242264516277L;
		private int idUsuario;
	    private RolDTO idRol;
	    private String nombre;
	    private String apellido;
	    private String email;
	    private String telefono;
	    private String direccion;
	    private String curp;
	    private String tipoSangre;
	    private String imagenUrl;
		
	    //constructor vacío
	    public UsuarioDTO() {
			super();
		}

		public UsuarioDTO(int idUsuario, RolDTO idRol, String nombre, String apellido, String email, String telefono,
				String direccion, String curp, String tipoSangre, String imagenUrl) {
			super();
			this.idUsuario = idUsuario;
			this.idRol = idRol;
			this.nombre = nombre;
			this.apellido = apellido;
			this.email = email;
			this.telefono = telefono;
			this.direccion = direccion;
			this.curp = curp;
			this.tipoSangre = tipoSangre;
			this.imagenUrl = imagenUrl;
		}
	    
	    

}