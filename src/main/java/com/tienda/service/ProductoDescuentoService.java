package com.tienda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tienda.DTO.DescuentoDTO;
import com.tienda.DTO.ProductoDTO;
import com.tienda.DTO.ProductoDescuentoDTO;
import com.tienda.entity.DescuentoEntity;
import com.tienda.entity.ProductoDescuentoEntity;
import com.tienda.entity.ProductoEntity;
import com.tienda.repository.IDescuentoRepository;
import com.tienda.repository.IProductoDescuentoRepository;
import com.tienda.repository.IProductoRepository;

@Service
public class ProductoDescuentoService implements IProductoDescuentoService {

	private final IProductoRepository iProductoRepository;
	private final IDescuentoRepository iDescuentoRepository;
	
	//contructor
	public ProductoDescuentoService(IProductoRepository iProductoRepository, IDescuentoRepository iDescuentoRepository) {
		this.iDescuentoRepository =iDescuentoRepository;
		this.iProductoRepository = iProductoRepository;
	}
	
	@Autowired
	private IProductoDescuentoRepository productoDescuentoRepository;
	@Autowired
	private ProductoService productoService;
	@Autowired
	private DescuentoService descuentoService;
		
//---METODOS---
	@Override
	public ProductoDescuentoDTO obtenerProductoDescuentoPorId(int id) {
		return productoDescuentoRepository.findById(id)
				.map(entity -> new ProductoDescuentoDTO(
						entity.getIdProductoDescuento(),
						descuentoService.convertirDescuentoADTO(entity.getIdDescuento())
						,
						productoService.convertirProductoADTO(entity.getIdProducto())
											
						))
						.orElse(null);
	}

	@Override
	public List<ProductoDescuentoDTO> obtenerTodosLosProductoDescuentos() {
		List<ProductoDescuentoEntity> lista = (List<ProductoDescuentoEntity>) productoDescuentoRepository.findAll();
		List<ProductoDescuentoDTO> listaDTO = new ArrayList<>();

		for(ProductoDescuentoEntity entity :lista) {
			//convertir idproducto, iddescuento entity a DTO
			ProductoDTO idproductoDTO = null;
			if(entity.getIdProducto() != null) {
				idproductoDTO = productoService.convertirProductoADTO(entity.getIdProducto());
			}
			
			DescuentoDTO iddescuentoDTO = null;
			if(entity.getIdDescuento() != null) {
				iddescuentoDTO =descuentoService.convertirDescuentoADTO(entity.getIdDescuento());
			}
			
			ProductoDescuentoDTO productodescdto = new ProductoDescuentoDTO(entity.getIdProductoDescuento(), iddescuentoDTO, idproductoDTO);
			listaDTO.add(productodescdto);
		}
		return listaDTO;
	}

	@Override
	public void eliminarProductoDescuento(int id) {
		if(!productoDescuentoRepository.existsById(id)) {
			throw new RuntimeException("Descuento de producto no encontrado");
			
		} productoDescuentoRepository.deleteById(id);
		
	}

	@Override
	public ProductoDescuentoDTO actualizarProductoDescuento(int id, ProductoDescuentoDTO productoDescuentoDTO) {
		try {
			ProductoDescuentoEntity descuentoExistente = productoDescuentoRepository.findById(id)
					.orElseThrow(()->new RuntimeException("Descuento de producto no encontrado con ID:  " + id));
			
			ProductoDescuentoEntity descuentoActualizado = productoDescuentoRepository.save(descuentoExistente);
			
			return new ProductoDescuentoDTO(
					descuentoActualizado.getIdProductoDescuento(),
					descuentoService.convertirDescuentoADTO(descuentoActualizado.getIdDescuento()),
					productoService.convertirProductoADTO(descuentoActualizado.getIdProducto())
				
					);
		}catch(Exception e) {
			throw new RuntimeException("Error al actualizar el descuento de producto:  " + e.getMessage());
		
		}
	}

	@Override
	public ProductoDescuentoDTO crearProductoDescuento(ProductoDescuentoDTO productoDescuentoDTO) {
		ProductoDescuentoEntity descuento = new ProductoDescuentoEntity();
		
		Optional<DescuentoEntity> iddescuento = this.iDescuentoRepository.findById(productoDescuentoDTO.getIdDescuento().getIdDescuento());			
		Optional<ProductoEntity> idproducto = this.iProductoRepository.findById(productoDescuentoDTO.getIdProducto().getIdproducto());
		
		descuento.setIdProducto(idproducto.get());
		descuento.setIdDescuento(iddescuento.get());
		
		ProductoDescuentoEntity descuentoGuardado = this.productoDescuentoRepository.save(descuento);
		
		return new ProductoDescuentoDTO(
				descuentoGuardado.getIdProductoDescuento(),
				descuentoService.convertirDescuentoADTO(descuentoGuardado.getIdDescuento()),
				productoService.convertirProductoADTO(descuentoGuardado.getIdProducto())
			
				);
	}
}
