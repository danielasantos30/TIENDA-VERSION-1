package com.tienda.service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.DTO.RolDTO;
import com.tienda.DTO.UsuarioDTO;
import com.tienda.entity.RolEntity;
import com.tienda.entity.UsuarioEntity;
import com.tienda.repository.IRolRepository;
import com.tienda.repository.IUsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {

    private final CategoriaService categoriaService;
	
	
	private final IRolRepository IRolRepository;

	@Autowired
    private  RolService rolService;
	
	@Autowired
	private  IUsuarioRepository usuarioRepository;

	 UsuarioService(IRolRepository IRolRepository, CategoriaService categoriaService) {
		this.IRolRepository =IRolRepository;
		this.categoriaService = categoriaService;
	}

	@Override
	public UsuarioDTO obtenerUsuarioPorId(int id) {
		return usuarioRepository.findById(id) 
			.map(entity -> new UsuarioDTO( 	
					entity.getIdUsuario(), 
					rolService.convertirRolADTO(entity.getIdRol()),
					entity.getNombre(),
					entity.getApellido(), 
					entity.getEmail(),
					entity.getTelefono(),
					entity.getDireccion(),
					entity.getCurp(),
					entity.getTipoSangre(),
					entity.getImagenUrl()
					)).orElse(null);
				
					
					
	}

	@Override
	public List<UsuarioDTO> obtenerTodosLosUsuarios() {
	    List<UsuarioEntity> listaUsuario = (List<UsuarioEntity>) usuarioRepository.findAll();
	    List<UsuarioDTO> listaUsuarioDTO = new ArrayList<>();

	    for (UsuarioEntity entity : listaUsuario) {
	        // convertir idrol Entity -> DTO
	        RolDTO idrolDTO = null;
	        if (entity.getIdRol() != null) {
	            idrolDTO = convertirIdRolADTO(entity.getIdRol());
	        }

	        // crear usuarioDTO porque el usuario depende de idrol que es un entity,
	        // hay que hacer conversion de entity a dto
	        UsuarioDTO usuariodto = new UsuarioDTO(entity.getIdUsuario(), idrolDTO, entity.getNombre(),
	                entity.getApellido(), entity.getEmail(), entity.getTelefono(), entity.getDireccion(), entity.getCurp(),entity.getTipoSangre(),entity.getImagenUrl());
	        listaUsuarioDTO.add(usuariodto);
	    }

	    return listaUsuarioDTO;
	}
	
	private RolDTO convertirIdRolADTO(RolEntity idRolEntity) {
		if (idRolEntity == null)
			return null;
		return new RolDTO(idRolEntity.getIdRol(), idRolEntity.getRol(),
				idRolEntity.getDescripcion());
	}

	@Override
	public void eliminarUsuario(int id) {
		if(!usuarioRepository.existsById(id)) {
			throw new RuntimeException("Usuario no encontrado con ID:  " + id);
		
		} usuarioRepository.deleteById(id);
		
		
	}

	@Override
	public UsuarioDTO actualizarUsuario(int id, UsuarioDTO usuarioDTO) {
	    try {
	        UsuarioEntity usuarioExistente = usuarioRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
	    
	        if(usuarioDTO.getNombre() != null) {
	            usuarioExistente.setNombre(usuarioDTO.getNombre().trim());
	        }
	        if(usuarioDTO.getApellido() != null) {
	            usuarioExistente.setApellido(usuarioDTO.getApellido().trim());
	        }
	        if(usuarioDTO.getEmail() != null) {
	            usuarioExistente.setEmail(usuarioDTO.getEmail().trim());
	        }
	        if(usuarioDTO.getTelefono() != null) {
	            usuarioExistente.setTelefono(usuarioDTO.getTelefono().trim());
	        }
	        if(usuarioDTO.getDireccion() != null) {
	            usuarioExistente.setDireccion(usuarioDTO.getDireccion());
	        }
	        if(usuarioDTO.getCurp() != null) {
	            usuarioExistente.setCurp(usuarioDTO.getCurp());
	        }
	        if(usuarioDTO.getTipoSangre() != null) {
	            usuarioExistente.setTipoSangre(usuarioDTO.getTipoSangre());
	      
	        }
	        if(usuarioDTO.getImagenUrl() != null) {
	            usuarioExistente.setImagenUrl(usuarioDTO.getImagenUrl());
	        }
	        
	    //guardar
	        UsuarioEntity usuarioActualizado = usuarioRepository.save(usuarioExistente);
	    //convertir a dto
	        return new UsuarioDTO(
	                usuarioActualizado.getIdUsuario(),
	                convertirIdRolADTO(usuarioActualizado.getIdRol()),
	                usuarioActualizado.getNombre(),
	                usuarioActualizado.getApellido(),
	                usuarioActualizado.getEmail(),
	                usuarioActualizado.getTelefono(),
	                usuarioActualizado.getDireccion(),
	                usuarioActualizado.getCurp(),
	        usuarioActualizado.getTipoSangre(),
	        usuarioActualizado.getImagenUrl());
	        
	    } catch (Exception e) {
	        throw new RuntimeException("Error al actualizar el usuario: " + e.getMessage());
	    }
	}

	@Override
	public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) throws ServiceException {
	    // Validaciones
	    if(usuarioDTO.getNombre() == null || usuarioDTO.getNombre().trim().isEmpty()) {
	        throw new ServiceException("El nombre es obligatorio");
	    }
	    if(usuarioDTO.getEmail() == null || usuarioDTO.getEmail().trim().isEmpty()) {
	        throw new ServiceException("El email es obligatorio");
	    }
	    
	    // Crear entity
	    UsuarioEntity usuario = new UsuarioEntity();
	    usuario.setNombre(usuarioDTO.getNombre());
	    usuario.setApellido(usuarioDTO.getApellido());
	    usuario.setEmail(usuarioDTO.getEmail());
	    usuario.setTelefono(usuarioDTO.getTelefono());
	    usuario.setDireccion(usuarioDTO.getDireccion());
	    usuario.setCurp(usuarioDTO.getCurp());
	    usuario.setTipoSangre(usuarioDTO.getTipoSangre());
	    usuario.setImagenUrl(usuarioDTO.getImagenUrl());
	    
	    // Buscar y asignar el Rol (similar a como haces con Categoria)
	    Optional<RolEntity> idRol = this.IRolRepository.findById(usuarioDTO.getIdRol().getIdRol());
	    usuario.setIdRol(idRol.get());
	    
	    // Guardar entity
	    UsuarioEntity usuarioGuardado = this.usuarioRepository.save(usuario);
	    
	    // Retornar DTO con ID generado
	    return new UsuarioDTO(
	        usuarioGuardado.getIdUsuario(),
	        convertirIdRolADTO(usuarioGuardado.getIdRol()),
	        usuarioGuardado.getNombre(),
	        usuarioGuardado.getApellido(),
	        usuarioGuardado.getEmail(),
	        usuarioGuardado.getTelefono(),
	        usuarioGuardado.getDireccion(),
	        usuarioGuardado.getCurp(),
	        usuarioGuardado.getTipoSangre(),
	        usuarioGuardado.getImagenUrl()
	    );
	    
	}
	
	public UsuarioDTO convertirUsuarioADTO(UsuarioEntity usuarioEntity) {
	 	   if (usuarioEntity == null)
	 		   return null;
	 	   return new UsuarioDTO (
	 			  usuarioEntity.getIdUsuario(),
	 			  convertirIdRolADTO(usuarioEntity.getIdRol()),
	 			  usuarioEntity.getNombre(),
	 			  usuarioEntity.getApellido(),
	 			  usuarioEntity.getEmail(),
	 			  usuarioEntity.getTelefono(),
	 			  usuarioEntity.getDireccion(),
	 			  usuarioEntity.getCurp(),
	 			  usuarioEntity.getTipoSangre(),
	 			  usuarioEntity.getImagenUrl());
	}
	

}
