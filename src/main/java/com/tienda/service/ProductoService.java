package com.tienda.service;

//import java.lang.foreign.Linker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.DTO.CategoriaDTO;
import com.tienda.DTO.ProductoDTO;
import com.tienda.entity.CategoriaEntity;
import com.tienda.entity.ProductoEntity;
import com.tienda.repository.ICategoriaRepository;
import com.tienda.repository.IProductoRepository;

@Service
public class ProductoService implements IProductoService {

	private final ICategoriaRepository ICategoriaRepository;

	@Autowired
	private CategoriaService categoriaService; // ← Inyectar el servicio para convertirCategoriaaDTO

	@Autowired
	// vamos a inyectar un objeto de tipo IproductoRepository
	private IProductoRepository productoRepository;

	ProductoService(ICategoriaRepository ICategoriaRepository) {
		this.ICategoriaRepository = ICategoriaRepository;
	}

	@Override
	public ProductoDTO obtenerProductoPorId(int id) {

		return productoRepository.findById(id) // me va a regresar un Opctional<Entity> de la BD, puede ser null o
												// Entity
				.map(entity -> new ProductoDTO( // el .map sirve para "Si hay un valor dentro del Optional,
												// transfórmalo. Si no hay nada, no hagas nada"
						entity.getIdproducto(), // me regresa un int
						categoriaService.convertirCategoriaADTO(entity.getIdcategoria()), // me regresa un DTO
																							// (CategoriaDTO)
						entity.getNombre(), entity.getPrecio(), entity.getMarca(), entity.getDescripcion(),
						entity.getImagenUrl()

				)).orElse(null);

	}

	@Override
	public List<ProductoDTO> obtenerTodosLosProductos() {
		List<ProductoEntity> listaProducto = (List<ProductoEntity>) productoRepository.findAll();
		List<ProductoDTO> listaProductoDTO = new ArrayList<>();

		for (ProductoEntity entity : listaProducto) {
			// convertir idcategoria Entity -> DTO
			CategoriaDTO idcategoriaDTO = null;
			if (entity.getIdcategoria() != null) {
				idcategoriaDTO = convertirIdCategoriaADTO(entity.getIdcategoria());
			}

			// crear productoDTO porque el producto depende de idcategoria que es un entity,
			// hay que hacer conversion de entity a dto
			ProductoDTO productodto = new ProductoDTO(entity.getIdproducto(), idcategoriaDTO, entity.getNombre(),
					entity.getPrecio(), entity.getMarca(), entity.getDescripcion(), entity.getImagenUrl());
			listaProductoDTO.add(productodto);
		}

		return listaProductoDTO;
	}

	private CategoriaDTO convertirIdCategoriaADTO(CategoriaEntity idcategoriaEntity) {
		if (idcategoriaEntity == null)
			return null;
		return new CategoriaDTO(idcategoriaEntity.getIdcategoria(), idcategoriaEntity.getNombre(),
				idcategoriaEntity.getDescripcion());
	}

	@Override
	public void eliminarProducto(int idproducto) {
		if (!productoRepository.existsById(idproducto)) {
			throw new RuntimeException("Producto no encontrado con ID:  " + idproducto);
		}
		productoRepository.deleteById(idproducto);
	}

	@Override
		public ProductoDTO actualizarProducto(int id, ProductoDTO productoDTO) {
			try {
				ProductoEntity productoExistente = productoRepository.findById(id)
						.orElseThrow(()->new RuntimeException("Producto no encontrado con ID:  " + id));
			
				if(productoDTO.getNombre() != null) {
					productoExistente.setNombre(productoDTO.getNombre().trim());
				}
				if(productoDTO.getPrecio() != null) {
					productoExistente.setPrecio(productoDTO.getPrecio());
				}
				if(productoDTO.getMarca() != null) {
					productoExistente.setMarca(productoDTO.getMarca().trim());
				}
				if(productoDTO.getDescripcion() != null) {
					productoExistente.setDescripcion(productoDTO.getDescripcion().trim());
				}
				if(productoDTO.getImagenUrl() !=null) {
					productoExistente.setImagenUrl(productoDTO.getImagenUrl().trim());
				}
				
			//guardar
				ProductoEntity productoActualizado = productoRepository.save(productoExistente);
			//convertir a dto
				return new ProductoDTO(
						productoActualizado.getIdproducto(),
						convertirIdCategoriaADTO( productoActualizado.getIdcategoria()),
						productoActualizado.getNombre(),
						productoActualizado.getPrecio(),
						productoActualizado.getMarca(),
						productoActualizado.getDescripcion(),
						productoActualizado.getImagenUrl());
				
			}catch (Exception e) {
				throw new RuntimeException("error al actualizar el producto:  "+ e.getMessage());
			}
		}

	@Override
	public ProductoDTO crearProducto(ProductoDTO productoDTO) throws ServiceException {
		//validaciones
		if(productoDTO.getNombre() == null || productoDTO.getNombre().trim().isEmpty()) {
			throw new ServiceException("El nombre es obligatorio");
		}
		//crear entity
		ProductoEntity producto = new ProductoEntity();
		producto.setNombre(productoDTO.getNombre());
		producto.setPrecio(productoDTO.getPrecio());
		producto.setMarca(productoDTO.getMarca());
		producto.setDescripcion(productoDTO.getDescripcion());
		producto.setImagenUrl(productoDTO.getImagenUrl());
		
		Optional<CategoriaEntity> idcategoria =this.ICategoriaRepository.findById(productoDTO.getIdcategoria().getIdcategoria());
		
		producto.setIdcategoria(idcategoria.get());
		
		//guardar entity
		ProductoEntity productoGuardado =this.productoRepository.save(producto);
		
		//retornar dto con ID generado
		return new ProductoDTO(
				productoGuardado.getIdproducto(),
				convertirIdCategoriaADTO(productoGuardado.getIdcategoria()),
				productoGuardado.getNombre(),
				productoGuardado.getPrecio(),
				productoGuardado.getMarca(),
				productoGuardado.getDescripcion(),
				productoGuardado.getImagenUrl()
				);
	}
	
	
	 public ProductoDTO convertirProductoADTO(ProductoEntity productoEntity) {
	 	   if (productoEntity == null)
	 		   return null;
	 	   return new ProductoDTO (
	 			   productoEntity.getIdproducto(),
	 			   convertirIdCategoriaADTO(productoEntity.getIdcategoria()),
	 			   productoEntity.getNombre(),
	 			   productoEntity.getPrecio(),
	 			   productoEntity.getMarca(),
	 			   productoEntity.getDescripcion(),
	 			  productoEntity.getImagenUrl()
//	 			   categoriaEntity.getFechaCreacion(),
//	 			   categoriaEntity.getFechaEliminacion(),
//	 			  categoriaEntity.getUsuarioCreacion(),
//	 			  convertirUsuarioADTO(categoriaEntity.getUsuarioEliminacion())
//	 	   
	 			   );
	    }

}
