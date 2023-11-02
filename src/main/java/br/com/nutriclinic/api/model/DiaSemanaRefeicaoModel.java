package br.com.nutriclinic.api.model;

import java.util.List;
import java.util.stream.Collectors;

import br.com.nutriclinic.domain.enuns.DiaSemana;
import br.com.nutriclinic.domain.repository.entity.PlanoAlimentarDiaSemana;

public class DiaSemanaRefeicaoModel {
	private Long id;
	private Integer diaSemana;
	private String descricaoDiaSemana;
	private List<RefeicaoModel> refeicoes;

	public DiaSemanaRefeicaoModel(PlanoAlimentarDiaSemana diaSemana) {
		this.id = diaSemana.getId();
		this.diaSemana = diaSemana.getDiaSemana();
		
		DiaSemana[] dias = DiaSemana.values();
		
		for (DiaSemana dia : dias) {
			if (diaSemana.getDiaSemana().equals(dia.getDiaSemana())) {
				descricaoDiaSemana = dia.getDescricaoDiaSemana();
				break;
			}
		}
				
		this.refeicoes = diaSemana.getRefeicoes().stream()
				.map(RefeicaoModel::new)
				.collect(Collectors.toList());
	}
	
	public Long getId() {
		return id;
	}

	public Integer getDiaSemana() {
		return diaSemana;
	}

	public String getDescricaoDiaSemana() {
		return descricaoDiaSemana;
	}

	public List<RefeicaoModel> getRefeicoes() {
		return refeicoes;
	}

}
