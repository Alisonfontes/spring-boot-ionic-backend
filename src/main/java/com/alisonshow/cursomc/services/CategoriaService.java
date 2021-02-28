package com.alisonshow.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alisonshow.cursomc.domain.Categoria;
import com.alisonshow.cursomc.repositories.CategoriaRepository;
import com.alisonshow.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired // intancia automatica pelo spring
	private CategoriaRepository repo;
	// buscando categoria pro codigo
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		
		/*if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado ID: " +  id + ", Tipo " + Categoria.class.getName());
		}
		*/
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objecto não encontrado ID: " + id + ", Tipo:  " + Categoria.class.getName()));
	}

}
