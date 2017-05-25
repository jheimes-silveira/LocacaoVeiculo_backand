package org.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.v1.TO.ClienteLocacaoTO;
import org.v1.domain.Cliente;
import org.v1.repository.ClienteRepository;
import org.v1.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController extends BatchController<Cliente, Integer> {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	public ClienteController(ClienteRepository rep) {
		super(rep);
	}
	
	/**
	 * busca os clientes que mais locaram veiculos em ordem decresente
	 * @return o numero de locacoes feitas pelos determinados clientes 
	 */
	@RequestMapping(value="/find/by/locacao/{mes}", method=RequestMethod.GET)
	public List<ClienteLocacaoTO> findByLocacao(@PathVariable Integer mes) {
		return clienteService.findByLocacao(mes);
	}

	/**
	 * busca os clientes que locaram veiculos no mes corrente
	 * @return o numero de locacoes feitas pelos determinados clientes 
	 */
	@RequestMapping(value="/find/by/mes/locacao/{mes}", method=RequestMethod.GET)
	public List<Cliente> findByLocacaoMes(@PathVariable Integer mes) {
		
		return clienteService.findByMesLocacao(mes);
	}
	
	@RequestMapping(value="/cpf/{cpf}", method=RequestMethod.GET)
	public List<Cliente> findByLocacaoMes(@PathVariable("cpf") String cpf) {
		
		return clienteService.findByCpf(cpf);
	}
}
