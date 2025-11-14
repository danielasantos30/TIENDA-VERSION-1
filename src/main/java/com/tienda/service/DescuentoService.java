package com.tienda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.DTO.DescuentoDTO;
import com.tienda.DTO.SucursalDTO;
import com.tienda.entity.DescuentoEntity;
import com.tienda.entity.SucursalEntity;
import com.tienda.repository.IDescuentoRepository;

	@Service
	public class DescuentoService implements IDescuentoService {
		
		@Autowired
		private IDescuentoRepository descuentoRepository;

		@Override
		public DescuentoDTO obtenerDescuentoPorId(int id) {
			return descuentoRepository.findById(id)
		            // Si encuentra la entidad, la convierte a DTO    
		            .map(entity -> new DescuentoDTO(
		                    entity.getIdDescuento(),
		                    entity.getNombreDescuento(),
		                    entity.getPorcentajeDesc(),
		                    entity.getFechaInicio(),
		                    entity.getFechaFin()
		                    
		                ))
		                .orElse(null);
		
		}

		@Override
		public List<DescuentoDTO> obtenerTodosLosDescuentos() {
			List<DescuentoEntity> listaDescuento = (List<DescuentoEntity>) descuentoRepository.findAll();
	        List<DescuentoDTO> listaDescuentoDTO = new ArrayList<>();
	      listaDescuento.forEach(
	    		  x-> listaDescuentoDTO.add(new DescuentoDTO(x.getIdDescuento(),x.getNombreDescuento(),x.getPorcentajeDesc(), x.getFechaInicio(),x.getFechaFin()))
	    		  
	    		  );
	        return listaDescuentoDTO;
		}

		@Override
		public DescuentoDTO eliminarDescuento(int idDescuento) {
			descuentoRepository.deleteById(idDescuento);
			
			return null;
		}

		@Override
		public DescuentoDTO actualizarDescuento(DescuentoDTO descuentoDTO) {
			//verificar existencia
	    	if(obtenerDescuentoPorId(descuentoDTO.getIdDescuento())== null) {
	    		return null;
	    	}
	    	//crear entity con los dto
	    	DescuentoEntity descuentoEntity =new DescuentoEntity();
	    	descuentoEntity.setIdDescuento(descuentoDTO.getIdDescuento());
	    	descuentoEntity.setNombreDescuento(descuentoDTO.getNombreDescuento());
	    	descuentoEntity.setPorcentajeDesc(descuentoDTO.getPorcentajeDesc());
	    	
	    	descuentoRepository.save(descuentoEntity);
	    	return descuentoDTO;
	    	
		
		}

		@Override
		public DescuentoDTO crearDescuento(DescuentoDTO descuentoDTO) {
			DescuentoEntity descuento =new DescuentoEntity();
	    	descuento.setNombreDescuento(descuentoDTO.getNombreDescuento());
	    	descuento.setPorcentajeDesc(descuentoDTO.getPorcentajeDesc());
	    	
	    	DescuentoEntity descuentoGuardado =this.descuentoRepository.save(descuento);
	    	
	    	descuentoDTO.setIdDescuento(descuentoGuardado.getIdDescuento());
	    	return descuentoDTO;
	       
		}
		
		
		public DescuentoDTO convertirDescuentoADTO(DescuentoEntity descuentoEntity) {
		 	   if (descuentoEntity == null)
		 		   return null;
		 	   return new DescuentoDTO (
		 			   descuentoEntity.getIdDescuento(),
		 			  descuentoEntity.getNombreDescuento(),
		 			  descuentoEntity.getPorcentajeDesc(),
		 			  descuentoEntity.getFechaInicio(),
		 			  descuentoEntity.getFechaFin() );
		}

	}


