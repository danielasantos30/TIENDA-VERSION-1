package com.tienda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.DTO.ClienteDTO;
import com.tienda.DTO.PedidosDTO;
import com.tienda.DTO.SucursalDTO;
import com.tienda.DTO.UsuarioDTO;
import com.tienda.entity.ClienteEntity;
import com.tienda.entity.PedidosEntity;
import com.tienda.entity.SucursalEntity;
import com.tienda.entity.UsuarioEntity;
import com.tienda.repository.IClienteRepository;
import com.tienda.repository.IPedidosRepository;
import com.tienda.repository.ISucursalRepository;
import com.tienda.repository.IUsuarioRepository;

 @Service
public class PedidosService implements IPedidosService {
	
	private final IClienteRepository iClienteRepository;
	private final IUsuarioRepository iUsuarioRepository;
	private final ISucursalRepository iSucursalRepository;

	//contructor
		public PedidosService(IClienteRepository iClienteRepository, IUsuarioRepository iUsuarioRepository, ISucursalRepository iSucursalRepository) {
			this.iClienteRepository =iClienteRepository;
			this.iUsuarioRepository = iUsuarioRepository;
			this.iSucursalRepository =iSucursalRepository;
		}
		
	@Autowired
	private IPedidosRepository pedidoRepository;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private SucursalService sucursalService;
	
	//METODOS
	@Override
	public PedidosDTO obtenerPedidoPorId(int id) {
		return pedidoRepository.findById(id)
				.map(entity -> new PedidosDTO(
						entity.getIdPedido(),
						clienteService.convertirClienteADTO(entity.getIdCliente())
						,
						usuarioService.convertirUsuarioADTO(entity.getIdUsuario())
						,
						sucursalService.convertirSucursalADTO(entity.getIdSucursal())
						,
						entity.getFechaPedido(),
						entity.getFechaCreacion(), entity.getEstatus()
						))
						.orElse(null);
	}
	
	@Override
	public List<PedidosDTO> obtenerTodosLosPedidos() {
		List<PedidosEntity> lista = (List<PedidosEntity>) pedidoRepository.findAll();
		List<PedidosDTO> listaDTO = new ArrayList<>();

		for(PedidosEntity entity :lista) {
			
			ClienteDTO idclienteDTO = null;
			if(entity.getIdCliente() != null) {
				idclienteDTO = clienteService.convertirClienteADTO(entity.getIdCliente());
			}
			
			UsuarioDTO idusuarioDTO = null;
			if(entity.getIdUsuario() != null) {
				idusuarioDTO =usuarioService.convertirUsuarioADTO(entity.getIdUsuario());
			}
			SucursalDTO idsucursalDTO = null;
			if(entity.getIdSucursal() != null) {
				idsucursalDTO =sucursalService.convertirSucursalADTO(entity.getIdSucursal());
			}
			
			
			PedidosDTO pedidodto = new PedidosDTO(entity.getIdPedido(), idclienteDTO, idusuarioDTO, idsucursalDTO, entity.getFechaPedido(), entity.getFechaCreacion(), entity.getEstatus());
			listaDTO.add(pedidodto);
		}
		return listaDTO;
	}
	
	@Override
	public void eliminarPedido(int id) {
		if(!pedidoRepository.existsById(id)) {
			throw new RuntimeException("Pedido no encontrado");
			
		} pedidoRepository.deleteById(id);
		
	}
	
	@Override
	public PedidosDTO actualizarPedido(int id, PedidosDTO pedidoDTO) {
		try {
			PedidosEntity pedidoExistente = pedidoRepository.findById(id)
					.orElseThrow(()->new RuntimeException("Pedido no encontrado con ID:  " + id));
			
			 if(pedidoDTO.getFechaPedido() != null) {
		            pedidoExistente.setFechaPedido(pedidoDTO.getFechaPedido());
		        }
			 
			 if(pedidoDTO.getFechaCreacion() != null) {
		            pedidoExistente.setFechaCreacion(pedidoDTO.getFechaCreacion());
		        }
			 
			 if(pedidoDTO.getEstatus() != null) {
		            pedidoExistente.setEstatus(pedidoDTO.getEstatus());
		        }
			 
			 
			
			PedidosEntity pedidoActualizado = pedidoRepository.save(pedidoExistente);
			
			return new PedidosDTO(
					pedidoActualizado.getIdPedido(),
					clienteService.convertirClienteADTO(pedidoActualizado.getIdCliente()),
					usuarioService.convertirUsuarioADTO(pedidoActualizado.getIdUsuario()),
					sucursalService.convertirSucursalADTO(pedidoActualizado.getIdSucursal()),
					pedidoActualizado.getFechaPedido(),
					pedidoActualizado.getFechaCreacion(),
					pedidoActualizado.getEstatus()
					);
		}catch(Exception e) {
			throw new RuntimeException("Error al actualizar el pedido de producto:  " + e.getMessage());
		
		}
	}
	
	@Override
	public PedidosDTO crearPedido(PedidosDTO pedidoDTO) {
		PedidosEntity pedido = new PedidosEntity();
		
		Optional<ClienteEntity> idcliente = this.iClienteRepository.findById(pedidoDTO.getIdCliente().getIdcliente());			
		Optional<UsuarioEntity> idusuario = this.iUsuarioRepository.findById(pedidoDTO.getIdUsuario().getIdUsuario());
		Optional<SucursalEntity> idsucursal = this.iSucursalRepository.findById(pedidoDTO.getIdSucursal().getIdSucursal());
	
		pedido.setIdCliente(idcliente.get());
		pedido.setIdUsuario(idusuario.get());
		pedido.setIdSucursal(idsucursal.get());
		
		PedidosEntity pedidoGuardado = this.pedidoRepository.save(pedido);
		
		return new PedidosDTO(
				pedidoGuardado.getIdPedido(),
				clienteService.convertirClienteADTO(pedidoGuardado.getIdCliente()),
				usuarioService.convertirUsuarioADTO(pedidoGuardado.getIdUsuario()),
				sucursalService.convertirSucursalADTO(pedidoGuardado.getIdSucursal()),
				pedidoGuardado.getFechaPedido(),
				pedidoGuardado.getFechaCreacion(),
				pedidoGuardado.getEstatus()
			
				);
	}


}
