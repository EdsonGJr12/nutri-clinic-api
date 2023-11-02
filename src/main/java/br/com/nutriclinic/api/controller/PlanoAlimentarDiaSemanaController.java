package br.com.nutriclinic.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriclinic.api.model.RefeicaoModel;
import br.com.nutriclinic.domain.exception.NegocioException;
import br.com.nutriclinic.domain.repository.PlanoAlimentarDiaSemanaRepository;
import br.com.nutriclinic.domain.repository.entity.PlanoAlimentarDiaSemana;

@RestController
@RequestMapping("/planos-alimentares-dia-semana")
public class PlanoAlimentarDiaSemanaController {
	
	@Autowired
	private PlanoAlimentarDiaSemanaRepository planoAlimentarDiaSemanaRepository;
	
	@GetMapping("/{idPlanoAlimentarDiaSemana}/refeicoes")
	public List<RefeicaoModel> pesquisarRefeicoes(@PathVariable Long idPlanoAlimentarDiaSemana) {
		PlanoAlimentarDiaSemana diaSemana = planoAlimentarDiaSemanaRepository.findById(idPlanoAlimentarDiaSemana)
			.orElseThrow(() -> new NegocioException("Recurso não encontrado"));
		return diaSemana.getRefeicoes().stream()
				.map(RefeicaoModel::new)
				.toList();
	}
}
