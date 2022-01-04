package com.jt.springboot2backend.services;

import java.util.Date;
import java.util.Optional;

import com.jt.springboot2backend.domain.Cliente;
import com.jt.springboot2backend.domain.ItemPedido;
import com.jt.springboot2backend.domain.PagamentoComBoleto;
import com.jt.springboot2backend.domain.Pedido;
import com.jt.springboot2backend.domain.enums.EstadoPagamento;
import com.jt.springboot2backend.repositories.ItemPedidoRepository;
import com.jt.springboot2backend.repositories.PagamentoRepository;
import com.jt.springboot2backend.repositories.PedidoRepository;
import com.jt.springboot2backend.security.UserSS;
import com.jt.springboot2backend.services.exceptions.AuthorizationException;
import com.jt.springboot2backend.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EmailService emailService;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		//emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}

	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) { 
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("acesso negado!");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Cliente cliente = clienteService.find(user.getId());
		return repo.findByCliente(cliente, pageRequest);
	}

}
