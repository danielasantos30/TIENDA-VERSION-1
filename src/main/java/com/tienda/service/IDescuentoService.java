package com.tienda.service;

import java.util.List;

import com.tienda.DTO.DescuentoDTO;

public interface IDescuentoService {
	
DescuentoDTO obtenerDescuentoPorId(int id);
	
	List<DescuentoDTO> obtenerTodosLosDescuentos();
	
    DescuentoDTO eliminarDescuento(int idDescuento);     
    
    DescuentoDTO actualizarDescuento(DescuentoDTO descuentoDTO); 
    
	DescuentoDTO crearDescuento(DescuentoDTO descuentoDTO);

}
