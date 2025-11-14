package com.tienda.service;

import java.util.List;

import com.tienda.DTO.RolDTO;

public interface IRolService {
	

	RolDTO obtenerRolPorId(int id);
	
	List<RolDTO> obtenerTodosLosRoles();
	
    RolDTO eliminarRol(int idrol);     
    
    RolDTO actualizarRol(RolDTO rolDTO); 
    
	RolDTO crearRol(RolDTO rolDTO);
	

}
