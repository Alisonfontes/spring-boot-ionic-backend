package com.alisonshow.cursomc.resources;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alisonshow.cursomc.domain.Pedido;
import com.alisonshow.cursomc.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResources {
	
	@Autowired
	private PedidoService service;

	@RequestMapping(value ="/{id}",  method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable  Integer id) {
		
		Pedido obj = service.buscar(id);
		
		return ResponseEntity.ok().body(obj);
		
		
	}
}
