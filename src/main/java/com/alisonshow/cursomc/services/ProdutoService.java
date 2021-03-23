package com.alisonshow.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alisonshow.cursomc.domain.Categoria;
import com.alisonshow.cursomc.domain.Produto;
import com.alisonshow.cursomc.repositories.CategoriaRepository;
import com.alisonshow.cursomc.repositories.ProdutoRepository;
import com.alisonshow.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired // intancia automatica pelo spring
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	// buscando categoria pro codigo
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		
		/*if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado ID: " +  id + ", Tipo " + Produto.class.getName());
		}
		*/
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objecto não encontrado ID: " + id + ", Tipo:  " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer>ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		List<Categoria>categorias = categoriaRepository.findAllById(ids);
		
		return repo.findDistinctByNomeContainingCategoriasIn(nome, categorias, pageRequest);
				
				
		
	}

}
