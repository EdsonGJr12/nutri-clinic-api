package br.com.nutriclinic.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriclinic.api.model.AlimentoModel;
import br.com.nutriclinic.domain.repository.AlimentoRepository;

@RestController
@RequestMapping("/alimentos")
public class AlimentoController {
	
	@Autowired
	private AlimentoRepository alimentoRepository;

	@GetMapping
	public List<AlimentoModel> pesquisarAlimentos(String descricao) {
		return alimentoRepository.findByDescricaoContaining(descricao).stream()
				.map(AlimentoModel::new)
				.toList();
	}
}
