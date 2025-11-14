package com.tienda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.DTO.RolDTO;
import com.tienda.entity.RolEntity;
import com.tienda.repository.IRolRepository;

@Service
public class RolService implements IRolService {
	
	@Autowired
	private IRolRepository rolRepository;

	@Override
	public RolDTO obtenerRolPorId(int id) {
		return rolRepository.findById(id)
	            // Si encuentra la entidad, la convierte a DTO    
	            .map(entity -> new RolDTO(
	                    entity.getIdRol(),
	                    entity.getRol(),
	                    entity.getDescripcion()                    
	                ))
	                .orElse(null);
	
	}

	@Override
	public List<RolDTO> obtenerTodosLosRoles() {
		List<RolEntity> listaRol = (List<RolEntity>) rolRepository.findAll();
        List<RolDTO> listaRolDTO = new ArrayList<>();
      listaRol.forEach(
    		  x-> listaRolDTO.add(new RolDTO(x.getIdRol(),x.getRol(),x.getDescripcion()))
    		  
    		  );
        return listaRolDTO;
	}

	@Override
	public RolDTO eliminarRol(int idrol) {
		rolRepository.deleteById(idrol);
		
		return null;
	}

	@Override
	public RolDTO actualizarRol(RolDTO rolDTO) {
		//verificar existencia
    	if(obtenerRolPorId(rolDTO.getIdRol())== null) {
    		return null;
    	}
    	//crear entity con los dto
    	RolEntity rolEntity =new RolEntity();
    	rolEntity.setIdRol(rolDTO.getIdRol());
    	rolEntity.setRol(rolDTO.getRol());
    	rolEntity.setDescripcion(rolDTO.getDescripcion());
    	
    	rolRepository.save(rolEntity);
    	return rolDTO;
    	
	
	}

	@Override
	public RolDTO crearRol(RolDTO rolDTO) {
		RolEntity rol =new RolEntity();
    	rol.setRol(rolDTO.getRol());
    	rol.setDescripcion(rolDTO.getDescripcion());
    	
    	RolEntity rolGuardado =this.rolRepository.save(rol);
    	
    	rolDTO.setIdRol(rolGuardado.getIdRol());
    	return rolDTO;
       
	}
	
	public RolDTO convertirRolADTO(RolEntity rolEntity) {
	 	   if (rolEntity == null)
	 		   return null;
	 	   return new RolDTO (
	 			   rolEntity.getIdRol(),
	 			   rolEntity.getRol(),
	 			   rolEntity.getDescripcion()
);
	    }

}
