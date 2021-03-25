package com.alisonshow.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alisonshow.cursomc.domain.ItemPedido;
import com.alisonshow.cursomc.domain.PagamentoComBoleto;
import com.alisonshow.cursomc.domain.Pedido;
import com.alisonshow.cursomc.domain.enums.EstadoPagamento;
import com.alisonshow.cursomc.repositories.ItemPedidoRepository;
import com.alisonshow.cursomc.repositories.PagamentoRepository;
import com.alisonshow.cursomc.repositories.PedidoRepository;
import com.alisonshow.cursomc.repositories.ProdutoRepository;
import com.alisonshow.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired // intancia automatica pelo spring
	private PedidoRepository repo;
	// buscando categoria pro codigo
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		
		/*if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado ID: " +  id + ", Tipo " + Pedido.class.getName());
		}
		*/
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objecto não encontrado ID: " + id + ", Tipo:  " + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preecherPagamentoComBoleto(pagto, obj.getInstante());
			
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}

}
