package br.com.nutriclinic.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriclinic.api.model.MedidaModel;
import br.com.nutriclinic.domain.repository.MedidaRepository;

@RestController
@RequestMapping("/medidas")
public class MedidaController {

	@Autowired
	private MedidaRepository medidaRepository;

	@GetMapping
	public List<MedidaModel> pesquisarAlimentos(String descricao) {
		return medidaRepository.findAll().stream()
				.map(MedidaModel::new)
				.toList();
	}
}
