package com.alisonshow.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alisonshow.cursomc.domain.Pedido;
import com.alisonshow.cursomc.repositories.PedidoRepository;
import com.alisonshow.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired // intancia automatica pelo spring
	private PedidoRepository repo;
	// buscando categoria pro codigo
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		
		/*if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado ID: " +  id + ", Tipo " + Pedido.class.getName());
		}
		*/
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objecto não encontrado ID: " + id + ", Tipo:  " + Pedido.class.getName()));
	}

}
