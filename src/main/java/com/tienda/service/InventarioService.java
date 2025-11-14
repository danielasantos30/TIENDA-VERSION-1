package com.tienda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tienda.DTO.InventarioDTO;
import com.tienda.DTO.ProductoDTO;
import com.tienda.DTO.SucursalDTO;

import com.tienda.entity.InventarioEntity;
import com.tienda.entity.ProductoEntity;
import com.tienda.entity.SucursalEntity;
import com.tienda.repository.IInventarioRepository;
import com.tienda.repository.IProductoRepository;
import com.tienda.repository.ISucursalRepository;


@Service
public class InventarioService implements IInventarioService {
	
	private final IProductoRepository IProductoRepository;
	private final ISucursalRepository ISucursalRepository;
	
	InventarioService(IProductoRepository IProductoRepository, ISucursalRepository ISucursalRepository) {
		this.IProductoRepository = IProductoRepository;
		this.ISucursalRepository = ISucursalRepository;
	}

	
	@Autowired
	private IInventarioRepository inventarioRepository;
	
	@Autowired
	private ProductoService productoService;
	@Autowired
	private SucursalService sucursalService;
	
	

	@Override
	public InventarioDTO obtenerInventarioPorId(int id) {
		return inventarioRepository.findById(id) 
			.map(entity -> new InventarioDTO(
			entity.getIdInventario(), // me regresa un int
			productoService.convertirProductoADTO(entity.getIdProducto()), 																		// (CategoriaDTO)
			sucursalService.convertirSucursalADTO(entity.getIdSucursal()),
			entity.getCantidad(), entity.getCantMinima()

)).orElse(null);
	}

	    
	@Override
	public List<InventarioDTO> obtenerTodosLosInventarios() {
		List<InventarioEntity> listaInventario = (List<InventarioEntity>) inventarioRepository.findAll();
		List<InventarioDTO> listaInventarioDTO = new ArrayList<>();

		for(InventarioEntity entity :listaInventario) {
			//convertir idproducto, idsucursal entity a DTO
			ProductoDTO idproductoDTO = null;
			if(entity.getIdProducto() != null) {
				idproductoDTO = productoService.convertirProductoADTO(entity.getIdProducto());
			}
			
			SucursalDTO idsucursalDTO = null;
			if(entity.getIdSucursal() != null) {
				idsucursalDTO =sucursalService.convertirSucursalADTO(entity.getIdSucursal());
			}
			
			InventarioDTO inventariodto = new InventarioDTO(entity.getIdInventario(),idproductoDTO, idsucursalDTO, entity.getCantidad(), entity.getCantMinima());
			listaInventarioDTO.add(inventariodto);
		}
		return listaInventarioDTO;
	}

	@Override
	public void eliminarInventario(int id) {
		if (!inventarioRepository.existsById(id)) {
			throw new RuntimeException("Inventario no encontrado con ID:  " + id);
		}
		inventarioRepository.deleteById(id);
		
	}

	@Override
	public InventarioDTO actualizarInventario(int id, InventarioDTO inventarioDTO) {
		try {
			InventarioEntity inventarioExistente = inventarioRepository.findById(id)
					.orElseThrow(()->new RuntimeException("Inventario no encontrado con ID:  " + id));
			if(inventarioDTO.getCantidad() != -1) {
				inventarioExistente.setCantidad(inventarioDTO.getCantidad());
			}
			if(inventarioDTO.getCantMinima() != -1) {
				inventarioExistente.setCantMinima(inventarioDTO.getCantMinima());
			}
			InventarioEntity inventarioActualizado = inventarioRepository.save(inventarioExistente);
			
			return new InventarioDTO(
					inventarioActualizado.getIdInventario(),
					productoService.convertirProductoADTO(inventarioActualizado.getIdProducto()),
					sucursalService.convertirSucursalADTO(inventarioActualizado.getIdSucursal()),
					inventarioActualizado.getCantidad(),
					inventarioActualizado.getCantMinima()
					);
		}catch(Exception e) {
			throw new RuntimeException("Error al actualizar el inventario:  " + e.getMessage());
		
		}
	}

	@Override
	public InventarioDTO crearInventario(InventarioDTO inventarioDTO) {
		InventarioEntity inventario = new InventarioEntity();
		inventario.setCantidad(inventarioDTO.getCantidad());
		inventario.setCantMinima(inventarioDTO.getCantMinima());
		
		Optional<ProductoEntity> idproducto = this.IProductoRepository.findById(inventarioDTO.getIdproducto().getIdproducto());
		Optional<SucursalEntity> idsucursal = this.ISucursalRepository.findById(inventarioDTO.getIdsucursal().getIdSucursal());
		
		inventario.setIdProducto(idproducto.get());
		inventario.setIdSucursal(idsucursal.get());
		
		InventarioEntity inventarioGuardado = this.inventarioRepository.save(inventario);
		
		return new InventarioDTO(
				inventarioGuardado.getIdInventario(),
				productoService.convertirProductoADTO(inventarioGuardado.getIdProducto()),
				sucursalService.convertirSucursalADTO(inventarioGuardado.getIdSucursal()),
				inventarioGuardado.getCantidad(),
				inventarioGuardado.getCantMinima()
				);
	}

}
