package com.alisonshow.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alisonshow.cursomc.domain.Categoria;
import com.alisonshow.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired // intancia automatica pelo spring
	private CategoriaRepository repo;
	// buscando categoria pro codigo
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}

}
