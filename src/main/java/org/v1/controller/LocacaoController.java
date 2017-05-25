package org.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.v1.domain.Avaria;
import org.v1.domain.Locacao;
import org.v1.domain.Multa;
import org.v1.repository.LocacaoRepository;

@RestController
@RequestMapping("/locacao")
public class LocacaoController extends BatchController<Locacao, Integer> {

	private LocacaoRepository locacaoRepository;
	@Autowired
	public LocacaoController(LocacaoRepository rep) {
		super(rep);
		locacaoRepository = rep;
	}
	
	@RequestMapping(value="/find/by/funcionario/{id}", method= RequestMethod.GET)
	public List<Locacao> findByFuncionario(@PathVariable("id") Integer id) {
		return locacaoRepository.findByFuncionario(id);
	}
	
	@RequestMapping(value="/find/by/veiculo/{id}", method= RequestMethod.GET)
	public List<Locacao> findByVeiculo(@PathVariable("id") Integer id) {
		return locacaoRepository.findByVeiculo(id);
	}

	@RequestMapping(value="/find/by/month/{mes}", method= RequestMethod.GET)
	public List<Locacao> findByMonth(@PathVariable("mes") Integer mes) {
		return locacaoRepository.findByMonth(mes);
	}
	
	@RequestMapping(value="/{idLocacao}/add/avarias", method= RequestMethod.POST)
	public Locacao addAvarias(@PathVariable("idLocacao") Integer idLocacao, @RequestBody List<Avaria> avarias) {
		Locacao locacao = locacaoRepository.findOne(idLocacao);
		locacao.setAvarias(avarias);
		return locacaoRepository.save(locacao);
	}
	
	@RequestMapping(value="/{idLocacao}/add/multas", method= RequestMethod.POST)
	public Locacao addMultas(@PathVariable("idLocacao") Integer idLocacao, @RequestBody List<Multa> multas) {
		Locacao locacao = locacaoRepository.findOne(idLocacao);
		locacao.setMultas(multas);
		return locacaoRepository.save(locacao);
	}
}
