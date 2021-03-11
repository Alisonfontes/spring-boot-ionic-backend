package com.alisonshow.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alisonshow.cursomc.domain.Categoria;
import com.alisonshow.cursomc.repositories.CategoriaRepository;
import com.alisonshow.cursomc.services.exceptions.DataIntegrityException;
import com.alisonshow.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired // intancia automatica pelo spring
	private CategoriaRepository repo;
	// buscando categoria pro codigo
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		
		/*if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado ID: " +  id + ", Tipo " + Categoria.class.getName());
		}
		*/
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objecto não encontrado ID: " + id + ", Tipo:  " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	//metodo de atualização
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	//delete
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
					
			
		}
		
	}
	
	// chamando todas as categorias
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	// paginacao
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

}
