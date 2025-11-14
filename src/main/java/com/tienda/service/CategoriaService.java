package com.tienda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.DTO.CategoriaDTO;
import com.tienda.DTO.UsuarioDTO;
import com.tienda.entity.CategoriaEntity;
import com.tienda.entity.UsuarioEntity;
import com.tienda.repository.ICategoriaRepository;
import com.tienda.repository.IUsuarioRepository; // ✅ NUEVO

@Service
public class CategoriaService implements ICategoriaService {
    
    @Autowired
    private ICategoriaRepository categoriaRepository;
    
    @Autowired
    private IUsuarioRepository usuarioRepository; //  Para buscar usuarios

    @Override
    public List<CategoriaDTO> obtenerTodasLasCategorias() {
        List<CategoriaEntity> listaCategoria = (List<CategoriaEntity>) categoriaRepository.findAll();
        List<CategoriaDTO> listaCategoriaDTO = new ArrayList<>();
      listaCategoria.forEach(
    		  x-> listaCategoriaDTO.add(new CategoriaDTO(x.getIdcategoria(),x.getNombre(),x.getDescripcion()))
    		  
    		  );
        return listaCategoriaDTO;
    }

    @Override
    public CategoriaDTO obtenerCategoriaPorId(int id) {
        return categoriaRepository.findById(id)
            // Si encuentra la entidad, la convierte a DTO    
            .map(entity -> new CategoriaDTO(
                    entity.getIdcategoria(),
                    entity.getNombre(),
                    entity.getDescripcion()                    
                ))
                .orElse(null);
        }

    @Override
    public CategoriaDTO eliminarCategoriaSiExiste(int idcategoria) {     
        categoriaRepository.deleteById(idcategoria);
        return null;
    }

   

    @Override
    public CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO) {
    	CategoriaEntity categoria =new CategoriaEntity();
    	categoria.setNombre(categoriaDTO.getNombre());
    	categoria.setDescripcion(categoriaDTO.getDescripcion());
    	
    	CategoriaEntity categoriaGuardada =this.categoriaRepository.save(categoria);
    	
    	categoriaDTO.setIdcategoria(categoriaGuardada.getIdcategoria());
    	return categoriaDTO;
       
        }
    

    @Override
    public CategoriaDTO actualizarCategoria(CategoriaDTO categoriaDTO) {
    	//verificar existencia
    	if(obtenerCategoriaPorId(categoriaDTO.getIdcategoria())== null) {
    		return null;
    	}
    	//crear entity con los dto
    	CategoriaEntity catEntity =new CategoriaEntity();
    	catEntity.setIdcategoria(categoriaDTO.getIdcategoria());
    	catEntity.setNombre(categoriaDTO.getNombre());
    	catEntity.setDescripcion(categoriaDTO.getDescripcion());
    	
    	categoriaRepository.save(catEntity);
    	return categoriaDTO;
    	
        }
    
    
    
    public CategoriaDTO convertirCategoriaADTO(CategoriaEntity categoriaEntity) {
 	   if (categoriaEntity == null)
 		   return null;
 	   return new CategoriaDTO (
 			   categoriaEntity.getIdcategoria(),
 			   categoriaEntity.getNombre(),
 			   categoriaEntity.getDescripcion()
// 			   categoriaEntity.getFechaCreacion(),
// 			   categoriaEntity.getFechaEliminacion(),
// 			  categoriaEntity.getUsuarioCreacion(),
// 			  convertirUsuarioADTO(categoriaEntity.getUsuarioEliminacion())
// 	   
 			   );
    }
    
//    
//    private UsuarioDTO convertirUsuarioADTO(UsuarioEntity usuarioEntity) {
//    	if (usuarioEntity == null)
//    		return null;
//        return new UsuarioDTO(
//            usuarioEntity.getIdUsuario(),
//            usuarioEntity.getIdRol(),
//            usuarioEntity.getNombre(),
//            usuarioEntity.getApellido(),
//            usuarioEntity.getEmail(),
//            
//        );
    }
    
  
