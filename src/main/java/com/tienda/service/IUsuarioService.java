package com.tienda.service;

import java.util.List;

import com.tienda.DTO.UsuarioDTO;

public interface IUsuarioService {
	
	 UsuarioDTO obtenerUsuarioPorId(int id);
	    
	    List<UsuarioDTO> obtenerTodosLosUsuarios();
	    
	    void eliminarUsuario(int id);
	    
	    UsuarioDTO actualizarUsuario(int id, UsuarioDTO usuarioDTO);
	    
	    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);

}
