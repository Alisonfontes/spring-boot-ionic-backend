package com.alisonshow.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alisonshow.cursomc.domain.Cliente;
import com.alisonshow.cursomc.repositories.ClienteRepository;
import com.alisonshow.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired // intancia automatica pelo spring
	private ClienteRepository repo;
	// buscando categoria pro codigo
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objecto não encontrado ID: " + id + ", Tipo:  " + Cliente.class.getName()));
	}

}
