package com.tienda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tienda.DTO.SucursalDTO;
import com.tienda.entity.SucursalEntity;
import com.tienda.repository.ISucursalRepository;

@Service
public class SucursalService implements ISucursalService {
	
	@Autowired
	private ISucursalRepository sucursalRepository;

	@Override
	public SucursalDTO obtenerSucursalPorId(int id) {
		return sucursalRepository.findById(id)
	            // Si encuentra la entidad, la convierte a DTO    
	            .map(entity -> new SucursalDTO(
	                    entity.getIdSucursal(),
	                    entity.getNombre(),
	                    entity.getDireccion(),
	                    entity.getTelefono(),
	                    entity.getUbicacion(),
	                    entity.getRfc(),
	                    entity.getImagenUrl(),
	                    entity.getLatitud(),
	                    entity.getLongitud()
	                    
	                ))
	                .orElse(null);
	
	}

	@Override
	public List<SucursalDTO> obtenerTodasLasSucursales() {
		List<SucursalEntity> listaSucursal = (List<SucursalEntity>) sucursalRepository.findAll();
        List<SucursalDTO> listaSucursalDTO = new ArrayList<>();
      listaSucursal.forEach(
    		  x-> listaSucursalDTO.add(new SucursalDTO(x.getIdSucursal(),x.getNombre(),x.getDireccion(),x.getTelefono(),x.getUbicacion(),x.getRfc(),x.getImagenUrl(),x.getLatitud(),x.getLongitud()))
    		  
    		  );
        return listaSucursalDTO;
	}

	@Override
	public SucursalDTO eliminarSucursal(int idSucursal) {
		sucursalRepository.deleteById(idSucursal);
		
		return null;
	}

	@Override
	public SucursalDTO actualizarSucursal(SucursalDTO sucursalDTO) {
		//verificar existencia
    	if(obtenerSucursalPorId(sucursalDTO.getIdSucursal())== null) {
    		return null;
    	}
    	//crear entity con los dto
    	SucursalEntity sucursalEntity =new SucursalEntity();
    	sucursalEntity.setIdSucursal(sucursalDTO.getIdSucursal());
    	sucursalEntity.setNombre(sucursalDTO.getNombre());
    	sucursalEntity.setDireccion(sucursalDTO.getDireccion());
    	
    	sucursalRepository.save(sucursalEntity);
    	return sucursalDTO;
    	
	
	}

	@Override
	public SucursalDTO crearSucursal(SucursalDTO sucursalDTO) {
		SucursalEntity sucursal =new SucursalEntity();
    	sucursal.setNombre(sucursalDTO.getNombre());
    	sucursal.setDireccion(sucursalDTO.getDireccion());
    	
    	SucursalEntity sucursalGuardada =this.sucursalRepository.save(sucursal);
    	
    	sucursalDTO.setIdSucursal(sucursalGuardada.getIdSucursal());
    	return sucursalDTO;
       
	}
	
	public SucursalDTO convertirSucursalADTO(SucursalEntity sucursalEntity) {
	 	   if (sucursalEntity == null)
	 		   return null;
	 	   return new SucursalDTO (
	 			   sucursalEntity.getIdSucursal(),
	 			   sucursalEntity.getNombre(),
	 			   sucursalEntity.getDireccion(),
	 			   sucursalEntity.getTelefono(),
	 			   sucursalEntity.getUbicacion(),
	 			   sucursalEntity.getRfc(),	 			  
	 			  sucursalEntity.getImagenUrl(),
	 			  sucursalEntity.getLatitud(),
	 			  sucursalEntity.getLongitud() );
	}

}