package com.tienda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tienda.DTO.ProductoDTO;
import com.tienda.DTO.SucursalDTO;
import com.tienda.DTO.TraspasoDTO;
import com.tienda.DTO.UsuarioDTO;
import com.tienda.entity.ProductoEntity;
import com.tienda.entity.SucursalEntity;
import com.tienda.entity.TraspasoEntity;
import com.tienda.entity.UsuarioEntity;
import com.tienda.repository.IProductoRepository;
import com.tienda.repository.ISucursalRepository;
import com.tienda.repository.ITraspasoRepository;
import com.tienda.repository.IUsuarioRepository;

@Service
public class TraspasoService implements ITraspasoService {
	@Autowired
	private ITraspasoRepository traspasoRepository;
	
	private final IProductoRepository iProductoRepository;
	private final IUsuarioRepository iUsuarioRepository;
	private final ISucursalRepository iSucursalRepository;
	
	public TraspasoService(IProductoRepository iProductoRepository, IUsuarioRepository iUsuarioRepository,
			ISucursalRepository iSucursalRepository) {
		this.iProductoRepository=iProductoRepository;
		this.iUsuarioRepository=iUsuarioRepository;
		this.iSucursalRepository= iSucursalRepository;
	}
	
	@Autowired
	private ProductoService productoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private SucursalService sucursalService;

	@Override
	public TraspasoDTO obtenerTraspasoPorId(int id) {
		return traspasoRepository.findById(id)
				.map(entity -> new TraspasoDTO(
						entity.getIdTraspaso(),
						productoService.convertirProductoADTO(entity.getIdProducto()),
						usuarioService.convertirUsuarioADTO(entity.getIdUsuario()),
						sucursalService.convertirSucursalADTO(entity.getSucursalOrigen()),
						sucursalService.convertirSucursalADTO(entity.getSucursalDestino()),
						entity.getCantidad(),						
						entity.getFechaTraspaso(),
						entity.getEstado()
						))
						.orElse(null);
	}
	@Override
	public List<TraspasoDTO> obtenerTodosLosTraspasos() {
		List<TraspasoEntity> lista = (List<TraspasoEntity>) traspasoRepository.findAll();
		List<TraspasoDTO> listaDTO = new ArrayList<>();

		for(TraspasoEntity entity :lista) {
			
			ProductoDTO idproductoDTO = null;
			if(entity.getIdProducto() != null) {
				idproductoDTO = productoService.convertirProductoADTO(entity.getIdProducto());
			}
			
			UsuarioDTO idusuarioDTO = null;
			if(entity.getIdUsuario() != null) {
				idusuarioDTO =usuarioService.convertirUsuarioADTO(entity.getIdUsuario());
			}
			SucursalDTO idsucursalDTO = null;
			if(entity.getSucursalOrigen() != null) {
				idsucursalDTO =sucursalService.convertirSucursalADTO(entity.getSucursalOrigen());
			}
			
			SucursalDTO idsucursaldestinoDTO = null;
			if(entity.getSucursalDestino() != null) {
				idsucursaldestinoDTO =sucursalService.convertirSucursalADTO(entity.getSucursalDestino());
			}
			
			TraspasoDTO traspasodto = new TraspasoDTO(entity.getIdTraspaso(), idproductoDTO, idusuarioDTO, idsucursalDTO, idsucursaldestinoDTO, entity.getCantidad(), entity.getFechaTraspaso(), entity.getEstado());
			listaDTO.add(traspasodto);
		}
			return listaDTO;
	
	}
	@Override
	public void eliminarTraspaso(int id) {
		if(!traspasoRepository.existsById(id)) {
			throw new RuntimeException("Traspaso no encontrado");
		} traspasoRepository.deleteById(id);
		
	}
	@Override
	public TraspasoDTO actualizarTraspaso(int id, TraspasoDTO traspasoDTO) {
		try {
			TraspasoEntity traspasoExistente = traspasoRepository.findById(id)
					.orElseThrow(()->new RuntimeException("Traspaso no encontrado con ID:  " + id));
			
			
			
			
			 if(traspasoDTO.getCantidad() != -1) {
		            traspasoExistente.setCantidad(traspasoDTO.getCantidad());
		        }
			 
			 if(traspasoDTO.getFechaTraspaso() != null) {
		            traspasoExistente.setFechaTraspaso(traspasoDTO.getFechaTraspaso());
		        }
			 
			 if(traspasoDTO.getEstado() != null) {
		            traspasoExistente.setEstado(traspasoDTO.getEstado());
		        }
	
			TraspasoEntity traspasoActualizado = traspasoRepository.save(traspasoExistente);
			
			return new TraspasoDTO(
					traspasoActualizado.getIdTraspaso(),
					productoService.convertirProductoADTO(traspasoActualizado.getIdProducto()),
					usuarioService.convertirUsuarioADTO(traspasoActualizado.getIdUsuario()),
					sucursalService.convertirSucursalADTO(traspasoActualizado.getSucursalOrigen()),
					sucursalService.convertirSucursalADTO(traspasoActualizado.getSucursalDestino()),
					traspasoActualizado.getCantidad(),
					traspasoActualizado.getFechaTraspaso(),
					traspasoActualizado.getEstado()
					);
		}catch(Exception e) {
			throw new RuntimeException("Error al actualizar el pedido de producto:  " + e.getMessage());
		
		}
	}
	@Override
	public TraspasoDTO crearTraspaso(TraspasoDTO traspasoDTO) {
			TraspasoEntity traspaso = new TraspasoEntity();
			traspaso.setCantidad(traspasoDTO.getCantidad());
			traspaso.setFechaTraspaso(traspasoDTO.getFechaTraspaso());
			traspaso.setEstado(traspasoDTO.getEstado());
			
					
		
		Optional<ProductoEntity> idproducto = this.iProductoRepository.findById(traspasoDTO.getIdProducto().getIdproducto());			
		Optional<UsuarioEntity> idusuario = this.iUsuarioRepository.findById(traspasoDTO.getIdUsuario().getIdUsuario());
		Optional<SucursalEntity> idsucursal = this.iSucursalRepository.findById(traspasoDTO.getSucursalOrigen().getIdSucursal());
		Optional<SucursalEntity> idsucursaldestino =this.iSucursalRepository.findById(traspasoDTO.getSucursalDestino().getIdSucursal());
		
		
		
		traspaso.setIdProducto(idproducto.get());
		traspaso.setIdUsuario(idusuario.get());
		traspaso.setSucursalOrigen(idsucursal.get());
		traspaso.setSucursalDestino(idsucursaldestino.get());
		
		TraspasoEntity traspasoGuardado = this.traspasoRepository.save(traspaso);
		
		return new TraspasoDTO(
				traspasoGuardado.getIdTraspaso(),
				productoService.convertirProductoADTO(traspasoGuardado.getIdProducto()),
				usuarioService.convertirUsuarioADTO(traspasoGuardado.getIdUsuario()),
				sucursalService.convertirSucursalADTO(traspasoGuardado.getSucursalOrigen()),
				sucursalService.convertirSucursalADTO(traspasoGuardado.getSucursalDestino()),
				traspasoGuardado.getCantidad(),
				traspasoGuardado.getFechaTraspaso(),
				traspasoGuardado.getEstado()
			
				);
	}
	
	

}
