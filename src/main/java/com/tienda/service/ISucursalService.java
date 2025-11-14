package com.tienda.service;

import java.util.List;

import com.tienda.DTO.SucursalDTO;

public interface ISucursalService {
	
SucursalDTO obtenerSucursalPorId(int id);
	
	List<SucursalDTO> obtenerTodasLasSucursales();
	
    SucursalDTO eliminarSucursal(int id);     
    
    SucursalDTO actualizarSucursal(SucursalDTO sucursalDTO); 
    
	SucursalDTO crearSucursal(SucursalDTO sucursalDTO);

}
