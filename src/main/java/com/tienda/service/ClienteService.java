package com.tienda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.DTO.ClienteDTO;
import com.tienda.DTO.DescuentoDTO;
import com.tienda.entity.ClienteEntity;
import com.tienda.entity.DescuentoEntity;
import com.tienda.repository.IClienteRepository;

@Service
public class ClienteService implements IClienteService {
	
	@Autowired
	private IClienteRepository clienteRepository;

	@Override
	public ClienteDTO obtenerClientePorId(int id) {
		return clienteRepository.findById(id)
				.map(entity -> new ClienteDTO(
						entity.getIdCliente(),
						entity.getNombre(),
						entity.getApellido(),
						entity.getEmail(),
						entity.getTelefono(),
						entity.getDireccion()
						))
		.orElse(null);
	}

	@Override
	public List<ClienteDTO> obtenerTodosLosClientes() {
		List<ClienteEntity> listaClientes =  (List<ClienteEntity>)clienteRepository.findAll();
		List<ClienteDTO> listaClientesDTO = new ArrayList<>();
		listaClientes.forEach(x-> listaClientesDTO.add(new ClienteDTO(x.getIdCliente(),x.getNombre(),x.getApellido(),x.getEmail(),x.getTelefono(),x.getDireccion())));
		
		return listaClientesDTO;
	}

	@Override
	public ClienteDTO eliminarCliente(int id) {
		clienteRepository.deleteById(id);
		return null;
	}

	@Override
	public ClienteDTO actualizarCliente(ClienteDTO clienteDTO) {
		if(obtenerClientePorId(clienteDTO.getIdcliente())== null) {
		return null;
	}
	ClienteEntity clienteEntity = new ClienteEntity();
	clienteEntity.setIdCliente(clienteDTO.getIdcliente());
	clienteEntity.setNombre(clienteDTO.getNombre());
	clienteEntity.setApellido(clienteDTO.getApellido());
	clienteEntity.setEmail(clienteDTO.getEmail());
	clienteEntity.setTelefono(clienteDTO.getTelefono());
	clienteEntity.setDireccion(clienteDTO.getDireccion());
	
	clienteRepository.save(clienteEntity);
	return clienteDTO;
	
}
	

	@Override
	public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
		ClienteEntity cliente = new ClienteEntity();
		cliente.setNombre(clienteDTO.getNombre());
		cliente.setApellido(clienteDTO.getApellido());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setTelefono(clienteDTO.getTelefono());
		cliente.setDireccion(clienteDTO.getDireccion());
		
		ClienteEntity clienteGuardado = this.clienteRepository.save(cliente);
		clienteDTO.setIdcliente(clienteGuardado.getIdCliente());
		return clienteDTO;
	}
	
	
	public ClienteDTO convertirClienteADTO(ClienteEntity clienteEntity) {
	 	   if (clienteEntity == null)
	 		   return null;
	 	   return new ClienteDTO (
	 			   clienteEntity.getIdCliente(),
	 			  clienteEntity.getNombre(),
	 			  clienteEntity.getApellido(),
	 			  clienteEntity.getEmail(),
	 			  clienteEntity.getTelefono(),
	 			  clienteEntity.getDireccion() );
	}


}
